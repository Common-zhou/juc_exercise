package framework;

/**
 * @author zhoubing
 * @date 2021-07-05 00:19
 */
public interface ITaskProcessor<T, R> {
    public TaskResult<R> execute(T data);
}
