package com.beiwu.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * @author zhoubing
 * @date 2021-06-20 23:51
 */
public class LearnThreadLocal {

    final ThreadLocal threadLocal = new ThreadLocal();
    final ThreadLocal threadLocal2 = new ThreadLocal();

    @Test
    public void testThreadLocal() throws InterruptedException {
        System.out.println("begin set ThreadLocal......");
        List<Thread> threads = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(() -> {
                String name = Thread.currentThread().getName();
                System.out.println(String.format("%s begin set threadLocal val.", name));

                Random random = new Random();
                int tmp = random.nextInt(5);

                threadLocal.set(String.format("%s set random %s", name, tmp));
                threadLocal2.set(String.format("%s set --- %s", name, tmp));

                try {
                    TimeUnit.SECONDS.sleep(tmp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Object val = threadLocal.get();
                Object val2 = threadLocal2.get();
                System.out.println(String.format("%s thread get value: %s", Thread.currentThread().getName(), val));
                System.out.println(String.format("%s thread get value --- : %s", Thread.currentThread().getName(), val2));
                latch.countDown();
            });
            threads.add(t1);
        }

        for (Thread thread : threads) {
            thread.start();
        }

        latch.await();
        System.out.println("all thread execute complete......");

    }



}
