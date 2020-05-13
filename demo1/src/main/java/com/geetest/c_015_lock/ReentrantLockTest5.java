package com.geetest.c_015_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-04-22 16:07
 */
public class ReentrantLockTest5 {
    Lock lock = new ReentrantLock();

    void method() {
        try {
            lock.lockInterruptibly();
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " has been interrupted");
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    void interrupt() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockTest5 reentrantLockTest5 = new ReentrantLockTest5();

        Thread thread1 = new Thread(reentrantLockTest5::method);
        thread1.start();

        TimeUnit.SECONDS.sleep(2);

        thread1.interrupt();

        thread1.join();



    }
}
