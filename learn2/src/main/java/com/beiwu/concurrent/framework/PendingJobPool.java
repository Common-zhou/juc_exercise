package com.beiwu.concurrent.framework;

import com.beiwu.concurrent.framework.vo.ITaskProcesser;
import com.beiwu.concurrent.framework.vo.JobInfo;
import com.beiwu.concurrent.framework.vo.TaskResult;
import com.beiwu.concurrent.framework.vo.TaskResultType;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2021-07-01 22:53
 */
public class PendingJobPool {
    // 框架运行时的线程数，与机器的CPU数相同
    private static final int THREAD_COUNTS = Runtime.getRuntime().availableProcessors();

    // 队列 线程池使用 用以存放待处理的任务
    private static BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(5000);

    // 线程池 固定大小 有界队列
    private static ExecutorService taskExecutor =
        new ThreadPoolExecutor(THREAD_COUNTS, THREAD_COUNTS, 60, TimeUnit.SECONDS, taskQueue);

    // 工作信息的存放容器
    private static ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    public static Map<String, JobInfo<?>> getMap() {
        return jobInfoMap;
    }

    private PendingJobPool() {
    }

    private static class JobPoolHolder {
        public static PendingJobPool pool = new PendingJobPool();
    }

    public static PendingJobPool getInstance(){
        return JobPoolHolder.pool;
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
            R r = null;
            ITaskProcesser<T, R> taskProcesser = (ITaskProcesser<T, R>) jobInfo.getTaskProcesser();

            TaskResult<R> result = null;
            try {
                result = taskProcesser.taskExecute(processData);
                if (result == null) {
                    result = new TaskResult<>(TaskResultType.Exception, r, "result is null");
                }
                if (result.getTaskResultType() == null) {
                    if (result.getReason() == null) {
                        result = new TaskResult<R>(TaskResultType.Exception, r
                            , "result is null");
                    } else {
                        result = new TaskResult<R>(TaskResultType.Exception, r
                            , "result is null,reason:"
                            + result.getReason());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = new TaskResult<R>(TaskResultType.Exception, r
                    , e.getMessage());
            } finally {
                jobInfo.addTaskResult(result);
            }


        }
    }

    private <R> JobInfo<R> getJob(String jobName) {
        JobInfo<R> jobInfo = (JobInfo<R>) jobInfoMap.get(jobName);
        if (null == jobInfo) {
            throw new RuntimeException(jobName + "是非法任务！");
        }
        return jobInfo;
    }

    public <T, R> void putTask(String jobName, T t) {
        JobInfo<R> jobInfo = getJob(jobName);

        PendingTask<T, R> task = new PendingTask<>(jobInfo, t);
        taskExecutor.execute(task);
    }

    public <R> void registerJob(String jobName, int jobLength,
                                ITaskProcesser<?, ?> taskProcesser, long expireTime) {
        JobInfo<R> jobInfo = new JobInfo<>(jobName, jobLength, taskProcesser, expireTime);
        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            throw new RuntimeException(jobName + "已经注册！");
        }

    }

    public <R> String getTaskProgess(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTotalProcess();
    }

    public <R> List<TaskResult<R>> getTaskDetail(String jobName) {
        JobInfo<R> jobInfo = getJob(jobName);
        return jobInfo.getTaskDetail();
    }
}
