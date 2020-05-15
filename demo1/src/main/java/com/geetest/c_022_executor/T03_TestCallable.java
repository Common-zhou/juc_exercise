package com.geetest.c_022_executor;

import java.util.concurrent.*;

/**
 * @author zhoubing
 * @date 2020-05-15 11:24
 */
public class T03_TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call method...");
                return "hello callable";
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<String> future = executorService.submit(callable);

        Future<?> runnableFuture = executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable run......");
            }
        });

        System.out.println(runnableFuture.get());
        System.out.println(future.get());
    }
}
