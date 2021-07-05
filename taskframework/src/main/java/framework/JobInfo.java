package framework;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 存放Job相关信息 JobName Task的结果
 *
 * @author zhoubing
 * @date 2021-07-05 00:17
 */
public class JobInfo<R> {
    private final String jobName;
    private final int jobLength;

    private final BlockingDeque<TaskResult<R>> resultQueue;
    private final ITaskProcessor<?, ?> iTaskProcessor;

    private final AtomicInteger successNum = new AtomicInteger(0);
    private final AtomicInteger totalProcessNum = new AtomicInteger(0);

    private CheckJobProcessor checkJobProcessor = CheckJobProcessor.getInstance();

    // 单位是s
    private final long expireTime;/*保留的工作的结果信息供查询的时长*/

    public JobInfo(String jobName, int jobLength,
                   ITaskProcessor<?, ?> iTaskProcessor,
                   long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.resultQueue = new LinkedBlockingDeque<>(jobLength);
        this.iTaskProcessor = iTaskProcessor;
        this.expireTime = expireTime;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobLength() {
        return jobLength;
    }

    public BlockingDeque<TaskResult<R>> getResultQueue() {
        return resultQueue;
    }

    public ITaskProcessor<?, ?> getiTaskProcessor() {
        return iTaskProcessor;
    }

    public int getSuccessNum() {
        return successNum.get();
    }

    public int getTotalNum() {
        return totalProcessNum.get();
    }

    public int getFailureNum() {
        return getTotalNum() - getSuccessNum();
    }

    // 进来的时候不做校验，但是在它的上游 必须做校验
    public void addTaskResult(TaskResult<R> result) {
        if (TaskResultType.Success.equals(result.getResultType())) {
            successNum.incrementAndGet();
        }
        totalProcessNum.incrementAndGet();

        resultQueue.addLast(result);

        if (jobLength == totalProcessNum.get()) {
            // 说明所有的都处理完成
            System.out.println(String.format("%s has processed all task", jobName));
            checkJobProcessor.putJob(jobName, expireTime);
        }

    }

    public List<TaskResult<R>> getTaskResults() {
        List<TaskResult<R>> results = new LinkedList<>();

        TaskResult<R> result;
        while ((result = resultQueue.pollFirst()) != null) {
            // 说明获取到了 则将其加入集合
            results.add(result);
        }
        return results;

    }

    public String getTotalProgress() {
        return "Success[" + successNum.get() + "]/Current[" + totalProcessNum.get()
                + "] Total[" + jobLength + "]";
    }
}
