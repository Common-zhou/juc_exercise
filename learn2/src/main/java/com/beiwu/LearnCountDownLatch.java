package com.beiwu;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch 就是 等待所有线程到达现场后 再往下接着走
 *
 * 只要线程池不需要统计执行时间，其实不需要使用CountDownLatch
 *
 * @author zhoubing
 * @date 2021-06-25 16:48
 */
public class LearnCountDownLatch {
    public static void main(String[] args) {
        //CountDownLatch

        int threadSize = 10;
        CountDownLatch latch = new CountDownLatch(threadSize);

        ExecutorService pool = Executors.newFixedThreadPool(4);

        for (int i = 0; i < threadSize; i++) {
            Random random = new Random();
            pool.execute(() -> {
                int sleepTime = random.nextInt(10);
                System.out.println(String.format("[ %s ] enter , begin sleep :%d s", Thread.currentThread().getName(), sleepTime));

                try {
                    TimeUnit.SECONDS.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                latch.countDown();
                System.out.println(String.format("[ %s ] out , end sleep :%d s", Thread.currentThread().getName(), sleepTime));
            });
        }
        pool.shutdown();

        System.out.println("All thread submit success.");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All thread execute success, exit the program.");


    }
}
