package com.beiwu.concurrent.framework.vo;

/**
 * @author zhoubing
 * @date 2021-07-01 21:48
 */
public class TaskResult<R> {
    private final TaskResultType taskResultType;

    private final R returnValue;

    private final String reason;

    public TaskResult(TaskResultType taskResultType, R returnValue, String reason) {
        this.taskResultType = taskResultType;
        this.returnValue = returnValue;
        this.reason = reason;
    }

    public TaskResult(TaskResultType taskResultType, R returnValue) {
        this.taskResultType = taskResultType;
        this.returnValue = returnValue;
        this.reason = "success";
    }

    public TaskResultType getTaskResultType() {
        return taskResultType;
    }

    public R getReturnValue() {
        return returnValue;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
            "taskResultType=" + taskResultType +
            ", returnValue=" + returnValue +
            ", reason='" + reason + '\'' +
            '}';
    }
}
