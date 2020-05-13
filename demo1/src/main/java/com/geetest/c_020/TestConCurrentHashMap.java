package com.geetest.c_020;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author zhoubing
 * @date 2020-05-13 08:54
 */
public class TestConCurrentHashMap {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        Thread[] threads = new Thread[100];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        Random random = new Random();

        long start = System.currentTimeMillis();

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    map.put("a" + random.nextInt(1000000), "a" + random.nextInt(10000000));
                }
                countDownLatch.countDown();
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("consume time:" + (System.currentTimeMillis() - start));
        System.out.println(map.size());


    }
}
