package com.geetest.c_015_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-04-21 23:13
 */
public class ReentrantLockTest1 {
    Lock lock = new ReentrantLock();
    static int count = 0;

    void method(){
        lock.lock();
        try {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest1 lockTest1 = new ReentrantLockTest1();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(lockTest1::method);
            threadList.add(thread);
        }
        threadList.forEach(Thread::start);
        threadList.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(count);

    }
}
