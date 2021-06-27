package com.beiwu.concurrent;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author zhoubing
 * @date 2021-06-21 00:35
 */
public class TestReference {

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 5,
        1, TimeUnit.MINUTES,
        new LinkedBlockingDeque<>());
    final static int TASK_LOOP_SIZE = 100;

    ThreadLocal<LocalVariable> threadLocal = new ThreadLocal();

    static class LocalVariable {
        private byte[] a = new byte[1024 * 1024 * 5];
    }

    @Test
    public void testLocal() throws InterruptedException {
        for (int i = 0; i < TASK_LOOP_SIZE; i++) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    LocalVariable localVariable = new LocalVariable();
                    threadLocal.set(localVariable);
                    System.out.println("use local variable");
                    threadLocal.remove();
                }
            });
            TimeUnit.MILLISECONDS.sleep(100);
        }

        TimeUnit.SECONDS.sleep(50);

        System.out.println("pool executor success");

    }

}
