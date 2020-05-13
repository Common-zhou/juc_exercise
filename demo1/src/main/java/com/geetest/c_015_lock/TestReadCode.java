package com.geetest.c_015_lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-05-04 11:39
 */
public class TestReadCode {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();

        lock.lock();
//        new Thread(() -> {
//            System.out.println("Thread start...");
//            lock.lock();
//            System.out.println("Thread got lock...");
//            try {
//                TimeUnit.SECONDS.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } finally {
//                lock.unlock();
//            }
//        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        lock.unlock();

    }
}
