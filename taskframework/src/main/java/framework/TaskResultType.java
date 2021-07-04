package framework;

/**
 * 这个类是用来存放任务执行状态的枚举
 * 成功 失败， 失败是代表执行状态失败。但不是碰到了异常之类的
 * 异常
 * @author zhoubing
 * @date 2021-07-05 00:10
 */
public enum TaskResultType {
    Success,
    Failure,
    Exception
}
