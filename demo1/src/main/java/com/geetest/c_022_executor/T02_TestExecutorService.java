package com.geetest.c_022_executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-15 11:21
 */
public class T02_TestExecutorService {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(()->{
            System.out.println("hello world");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(executorService.isShutdown());

        TimeUnit.SECONDS.sleep(2);

        System.out.println(executorService.isShutdown());

        executorService.shutdown();

        System.out.println(executorService.isShutdown());

    }
}
