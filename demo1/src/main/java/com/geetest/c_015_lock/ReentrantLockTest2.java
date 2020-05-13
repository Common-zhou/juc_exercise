package com.geetest.c_015_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-04-22 15:28
 */
public class ReentrantLockTest2 {

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
        ReentrantLockTest2 reentranLockTest2 = new ReentrantLockTest2();

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(reentranLockTest2::method));
        }

        threadList.forEach(Thread::start);
        threadList.forEach(o->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(count);

    }
}
