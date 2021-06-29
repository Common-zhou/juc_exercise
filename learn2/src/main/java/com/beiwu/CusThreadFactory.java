package com.beiwu;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2021-06-25 17:06
 */
public class CusThreadFactory {
    public static void main(String[] args) {
        int threadSize = 10;

        TimeThreadPoolExecutor pool = new TimeThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

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
                System.out.println(String.format("[ %s ] out , end sleep :%d s", Thread.currentThread().getName(), sleepTime));
            });
        }

        pool.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

        pool.shutdown();
    }
}
