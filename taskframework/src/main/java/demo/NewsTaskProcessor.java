package demo;

import framework.ITaskProcessor;
import framework.TaskResult;
import framework.TaskResultType;
import framework.utils.SleepTools;

/**
 * @Author zhoubing
 * @Date 2021-07-05 10:15
 */
public class NewsTaskProcessor implements ITaskProcessor<Integer, String> {
    @Override
    public TaskResult<String> execute(Integer id) {
        TaskResult<String> taskResult = null;

        if (id % 7 == 0) {
            throw new IllegalArgumentException(String.format("%s is illegal.", id));
        } else if (id % 11 == 0) {
            // 模拟没有设置值的情况
            return taskResult;
        }

        SleepTools.ms(500);

        taskResult = new TaskResult<>(TaskResultType.Success, Thread.currentThread().getName() + "--" + id, "process normal.");

        return taskResult;
    }
}
