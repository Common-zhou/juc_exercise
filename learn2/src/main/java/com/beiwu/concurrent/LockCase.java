package com.beiwu.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2021-06-27 23:15
 */
public class LockCase {

    private int number = 0;
    private Lock lock = new ReentrantLock();

    static class TestThread extends Thread {

        private LockCase lockCase;

        public TestThread(LockCase lockCase) {
            this.lockCase = lockCase;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                test();
            }
            System.out.println(String.format("add last number is :%d", lockCase.number));
        }

        public void test() {
            lockCase.lock.lock();
            try {
                lockCase.number++;
            } finally {
                lockCase.lock.unlock();
            }
        }

    }

    public void test2() {
        lock.lock();
        try {
            number--;
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        LockCase lockCase = new LockCase();

        TestThread thread = new TestThread(lockCase);
        thread.start();

        for (int i = 0; i < 10000; i++) {
            lockCase.test2();
        }

        TimeUnit.SECONDS.sleep(5);

        System.out.println(String.format("[MAIN-THREAD] add last number is :%d", lockCase.number));


    }
}
