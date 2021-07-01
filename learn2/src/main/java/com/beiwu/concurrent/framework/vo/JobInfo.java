package com.beiwu.concurrent.framework.vo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2021-07-01 22:18
 */
public class JobInfo<R> {
    private final String jobName;
    private final int jobLength;
    private final ITaskProcesser<?, ?> taskProcesser;

    private AtomicInteger successCount;
    private AtomicInteger taskProcessCount;

    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueues;

    private final long expireTime;

    public JobInfo(String jobName, int jobLength,
                   ITaskProcesser<?, ?> taskProcesser,
                   long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        successCount = new AtomicInteger(0);
        taskProcessCount = new AtomicInteger(0);
        this.taskProcesser = taskProcesser;
        taskDetailQueues = new LinkedBlockingDeque<TaskResult<R>>(jobLength);
        this.expireTime = expireTime;
    }

    public int getSuccCount() {
        return successCount.get();
    }

    public int getTaskProcessCount() {
        return taskProcessCount.get();
    }

    //提供工作中失败的次数
    public int getFailCount() {
        return taskProcessCount.get() - successCount.get();
    }

    public ITaskProcesser<?, ?> getTaskProcesser() {
        return taskProcesser;
    }

    public int getJobLength() {
        return jobLength;
    }

    /*提供工作的整体进度信息*/
    public String getTotalProcess() {
        return "Success[" + successCount.get() + "]/Current[" + taskProcessCount.get()
            + "] Total[" + jobLength + "]";
    }

    /*提供工作中每个任务的处理结果*/
    public List<TaskResult<R>> getTaskDetail() {
        List<TaskResult<R>> taskResults = new LinkedList<>();

        TaskResult<R> tr = null;
        while ((tr = taskDetailQueues.pollFirst()) != null) {
            // 如果获取到了 说明就有结果
            taskResults.add(tr);
        }
        return taskResults;

    }

    public void addTaskResult(TaskResult<R> taskResult){
        if (TaskResultType.Success.equals(taskResult.getTaskResultType())){
            // 成功
            successCount.incrementAndGet();
        }
        taskProcessCount.incrementAndGet();

        taskDetailQueues.addLast(taskResult);
        if(taskProcessCount.get()==jobLength){
            // 说明已经全部执行完成
        }
    }

}
