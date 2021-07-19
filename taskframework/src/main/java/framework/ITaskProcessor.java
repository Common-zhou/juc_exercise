package framework;

/**
 * 执行Task的业务流程方法
 *
 * @author zhoubing
 * @date 2021-07-05 00:19
 */
public interface ITaskProcessor<T, R> {
    TaskResult<R> execute(T data);
}
