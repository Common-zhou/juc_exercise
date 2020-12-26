package com.beiwu.ch4.rwl;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhoubing
 * @date 2020-12-27 00:26
 */
public class UseReadWriteLock {
    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    private static Lock readLock = lock.readLock();
    private static Lock writeLock = lock.writeLock();

    private static CountDownLatch latch = new CountDownLatch(100);
    private static int count = 0;

    public static void increase() {
        writeLock.lock();

        try {
            for (int i = 0; i < 10; i++) {
                count++;
            }
            SleepTools.ms(500);
        } finally {
            latch.countDown();
            writeLock.unlock();
        }
    }

    public static void read() {
        readLock.lock();

        try {
            for (int i = 0; i < 100; i++) {
                System.out.println("读得的值为:" + count);
            }
        } finally {
            latch.countDown();
            readLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long srartTime = System.currentTimeMillis();
        for (int i = 0; i < 90; i++) {
            new Thread(() -> {
                read();
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                increase();
            }).start();
        }

        System.out.println("All thread init success......");

        latch.await();
        System.out.println("Consume Time: " + (System.currentTimeMillis() - srartTime));

        System.out.println("All thread done......");
        System.out.println("current number:" + count);
    }
}
