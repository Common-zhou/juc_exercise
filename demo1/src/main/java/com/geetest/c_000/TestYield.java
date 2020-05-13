package com.geetest.c_000;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-04-19 14:10
 */
public class TestYield {
    static Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 100; i++) {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + " ：" + i);
                lock.unlock();
                Thread.yield();
            }
        };

        Thread thread1 = new Thread(runnable, "A");
        Thread thread2 = new Thread(runnable, "B");
        thread1.join();
        thread2.join();

        thread1.start();
        thread2.start();

    }
}
