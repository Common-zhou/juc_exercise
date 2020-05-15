package com.geetest.c_022_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoubing
 * @date 2020-05-15 12:05
 */
public class T07_TestSingleThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + " has been started...");
            });
        }

    }
}
