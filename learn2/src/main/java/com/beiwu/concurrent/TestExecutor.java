package com.beiwu.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2021-06-22 21:25
 */
public class TestExecutor {

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        Random random = new Random();

        AtomicInteger atomic = new AtomicInteger(0);

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
