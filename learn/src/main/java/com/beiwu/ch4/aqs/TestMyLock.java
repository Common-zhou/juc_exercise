package com.beiwu.ch4.aqs;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 * @author zhoubing
 * @date 2020-12-27 17:23
 */
public class TestMyLock {
    public void test(){
        final Lock lock = new SelfLock();

        class Worker extends Thread{
            @Override
            public void run() {
                lock.lock();

                System.out.println(Thread.currentThread().getName());
                try {
                    SleepTools.second(1);
                } finally {
                    lock.unlock();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            Worker w = new Worker();
            w.start();
        }

        for (int i = 0; i < 10; i++) {
            SleepTools.second(1);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock = new TestMyLock();
        testMyLock.test();
    }
}
