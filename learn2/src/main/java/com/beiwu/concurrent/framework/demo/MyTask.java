package com.beiwu.concurrent.framework.demo;

import com.beiwu.concurrent.SleepTools;
import com.beiwu.concurrent.framework.vo.ITaskProcesser;
import com.beiwu.concurrent.framework.vo.TaskResult;
import com.beiwu.concurrent.framework.vo.TaskResultType;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2021-07-02 00:34
 */
public class MyTask implements ITaskProcesser<Integer, Integer> {
    @Override
    public TaskResult<Integer> taskExecute(Integer data) {

        Random random = new Random();
        int tmp = random.nextInt(500);

        SleepTools.sleep(tmp);

        if (tmp < 300) {
            // 正常返回
            return new TaskResult<>(TaskResultType.Success, tmp, "ok");
        } else if (tmp > 301 && tmp <= 400) {//处理失败的情况
            return new TaskResult<Integer>(TaskResultType.Failure, -1, "Failure");
        } else {//发生异常的情况
            try {
                throw new RuntimeException("异常发生了！！");
            } catch (Exception e) {
                return new TaskResult<Integer>(TaskResultType.Exception,
                    -1, e.getMessage());
            }
        }

    }
}
