package com.beiwu.concurrent;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2021-06-22 21:53
 */
public class TestCusExecutor {
    public static void main(String[] args) {

        CusTerminatedThreadPoolExecutor service =
            new CusTerminatedThreadPoolExecutor(4, 4, 0, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());

        AtomicInteger atomic = new AtomicInteger(0);

        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            service.execute(() -> {
                System.out.println(
                    String.format("[ %s ] begin thread...", Thread.currentThread().getName()));

                int tmp = random.nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int num = atomic.incrementAndGet();

                System.out.println(
                    String
                        .format("[ %s ] end thread... number: %d", Thread.currentThread().getName(),
                            num));
            });
        }

        service.shutdown();



    }
}
