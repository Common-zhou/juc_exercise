package framework;

/**
 * 用以表示结果的类，
 *
 * @author zhoubing
 * @date 2021-07-05 00:14
 */
public class TaskResult<R> {
    private TaskResultType resultType;

    // 执行结果
    private R data;

    // 成功可以带成功的消息  失败可以带失败的原因
    private String message;

    public TaskResult(TaskResultType resultType, R data, String message) {
        this.resultType = resultType;
        this.data = data;
        this.message = message;
    }

    public TaskResultType getResultType() {
        return resultType;
    }

    public R getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
