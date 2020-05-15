package com.geetest.c_022_executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhoubing
 * @date 2020-05-15 11:42
 */
public class T05_TestFutureTask {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(()->{
            System.out.println("Future Task running......");
            TimeUnit.SECONDS.sleep(2);
            return "hello future task";
        });

        new Thread(futureTask).start();

//        String s = futureTask.get(1, TimeUnit.SECONDS);
        String s = futureTask.get();
        System.out.println(s);
    }
}
