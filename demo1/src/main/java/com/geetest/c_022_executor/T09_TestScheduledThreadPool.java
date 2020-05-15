package com.geetest.c_022_executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-15 13:54
 */
public class T09_TestScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);

        scheduledThreadPool.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.MICROSECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, 0, 1000, TimeUnit.MICROSECONDS);

    }
}
