package com.beiwu.ch4.aqs;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-29 10:21
 * 测试可重入锁
 */
public class TestSelfReentrantLock {
    private SelfReentrantLock lock = new SelfReentrantLock();

    public void recursiveEnterThread(int number) {
        lock.lock();

        try {
            if (number == 0) {
                // 递归结束
                return;
            }
            System.out.println(Thread.currentThread().getName() + " enter_" + number);
            recursiveEnterThread(number - 1);
            SleepTools.second(1);
            System.out.println(Thread.currentThread().getName() + " leave_" + number);
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        TestSelfReentrantLock testObject = new TestSelfReentrantLock();
        for (int i = 0; i < 1; i++) {
            new Thread(()->{
                testObject.recursiveEnterThread(5);
            }).start();
        }
        System.out.println("test Thread has init success......");
    }
}
