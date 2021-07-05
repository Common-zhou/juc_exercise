package framework;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 这是用来提交多任务的主类
 * 所有任务都是从该类提交
 * 所有任务信息都是从该类获取
 *
 * @author zhoubing
 * @date 2021-07-05 00:40
 */
public class PendingJobPool {

    // 这个可以拿到当前CPU的核数
    private static final Integer TOTAL_THREAD_NUMBER = 50;
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    private static final BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);

    private static final ExecutorService pool = new ThreadPoolExecutor(TOTAL_THREAD_NUMBER, TOTAL_THREAD_NUMBER, 1, TimeUnit.SECONDS, taskQueue);

    private PendingJobPool() {
    }

    private static class PendingJobHolder {
        static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance() {
        return PendingJobHolder.pool;
    }

    public static Map<String, JobInfo<?>> getMap() {
        return jobInfoMap;
    }

    private static class PendingTask<T, R> implements Runnable {

        private JobInfo<R> jobInfo;
        private T processData;

        public PendingTask(JobInfo<R> jobInfo, T processData) {
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @Override
        public void run() {
            ITaskProcessor<T, R> iTaskProcessor = (ITaskProcessor<T, R>) jobInfo.getiTaskProcessor();
            TaskResult<R> taskResult = null;
            R data = null;
            try {
                taskResult = iTaskProcessor.execute(processData);

                // 这代表 里面的操作根本没有向 TaskResult赋值
                if (taskResult == null) {
                    // 代表错误 没有返回给我Result  即认为任务执行失败
                    taskResult = new TaskResult<>(TaskResultType.Failure, data, "taskResult is null.Please check process.");
                }
                // 这代表没有设置状态 那我认为它是执行失败的
                if (taskResult.getResultType() == null) {
                    if (taskResult.getMessage() != null) {
                        taskResult = new TaskResult<>(TaskResultType.Failure, data, taskResult.getMessage());
                    } else {
                        taskResult = new TaskResult<>(TaskResultType.Failure, data, "taskResult restType is null,and message is null.");
                    }
                }

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(String.format("ERROR with message: %s", e.getMessage()));
                taskResult = new TaskResult<>(TaskResultType.Exception, data, e.getMessage());
            }
            jobInfo.addTaskResult(taskResult);
        }
    }

    public void registerJob(String jobName, ITaskProcessor<?, ?> iTaskProcessor, int jobLength, long expireTime) {
        JobInfo<Object> jobInfo = new JobInfo<>(jobName, jobLength, iTaskProcessor, expireTime);

        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            // 代表之前注册过该任务
            throw new RuntimeException(String.format("%s job name has been registered.", jobName));
        }
    }

    public <R> JobInfo<R> getJob(String jobName) {
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (jobInfo == null) {
            throw new RuntimeException(String.format("%s job is not register.", jobName));
        }
        return jobInfo;
    }

    public <T, R> void putTask(String jobName, T processData) {
        JobInfo<R> jobInfo = getJob(jobName);

        PendingTask<T, R> task = new PendingTask<>(jobInfo, processData);
        pool.execute(task);
    }

    // 获取job的执行状态
    public <R> String getProgress(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);

        return jobInfo.getTotalProgress();

    }

    public <R> List<TaskResult<R>> getTaskDetail(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);

        return jobInfo.getTaskResults();
    }
}
