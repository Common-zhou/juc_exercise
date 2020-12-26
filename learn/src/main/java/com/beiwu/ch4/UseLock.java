package com.beiwu.ch4;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-12-27 00:12
 */
public class UseLock {
    private static Lock lock = new ReentrantLock();
    private static int number = 0;

    private static void method1() {
        lock.lock();

        try {
            number++;
            SleepTools.ms(2);
            System.out.println(number);
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                method1();
            }).start();
        }
        System.out.println("Main thread 结束------");
    }
}
