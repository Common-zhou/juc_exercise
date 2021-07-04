package framework;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是用来提交多任务的主类
 * 所有任务都是从该类提交
 * 所有任务信息都是从该类获取
 *
 * @author zhoubing
 * @date 2021-07-05 00:40
 */
public class PendingJobPool {

    private ConcurrentHashMap<String, JobInfo<?>> jobInfoMap = new ConcurrentHashMap<>();

    private PendingJobPool() {
    }

    private static class PendingJobHolder {
        static PendingJobPool pool = new PendingJobPool();
    }

    public PendingJobPool getInstance() {
        return PendingJobHolder.pool;
    }

    public void registerJob(String jobName, ITaskProcessor<?, ?> iTaskProcessor, int jobLength) {
        JobInfo<Object> jobInfo = new JobInfo<>(jobName, jobLength, iTaskProcessor);

        if (jobInfoMap.putIfAbsent(jobName, jobInfo) != null) {
            // 代表之前注册过该任务
            throw new RuntimeException(String.format("{} job name has been registered.", jobName));
        }
    }


}
