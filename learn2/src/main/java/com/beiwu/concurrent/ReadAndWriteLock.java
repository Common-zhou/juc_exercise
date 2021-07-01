package com.beiwu.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁使用
 * 如果所有的操作都加锁 那会非常慢
 * 如果读写锁分离，那有写锁的时候 读锁无法获得 ；但只有读锁的时候 读锁可以继续获得
 *
 * @author zhoubing
 * @date 2021-06-28 22:47
 */
public class ReadAndWriteLock {

    static class TestReadLock implements Runnable {
        private Lock lock;

        public TestReadLock(Lock lock) {
            this.lock = lock;
        }

        public static void workTimeConsume() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            lock.lock();
            System.out.println(String.format("read thread [%s] begin thread", Thread.currentThread().getName()));
            try {
                workTimeConsume();
            } finally {
                lock.unlock();
            }
            System.out.println(String.format("read thread  [%s] end thread", Thread.currentThread().getName()));

        }
    }

    static class TestWriteLock extends Thread {
        private Lock lock;

        public TestWriteLock(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            System.out.println(String.format("[%s] begin thread", Thread.currentThread().getName()));
            try {
                workTimeConsume();
            } finally {
                lock.unlock();
            }
            System.out.println(String.format("[%s] end thread", Thread.currentThread().getName()));
        }

        private void workTimeConsume() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();


        //Lock lock = new ReentrantLock();
        //Lock readLock = lock;
        //Lock writeLock = lock;

        List<Thread> readThreads = new ArrayList<>();
        List<TestWriteLock> writeThreads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            TestReadLock run = new TestReadLock(readLock);
            readThreads.add(new Thread(run));
        }

        for (int i = 0; i < 2; i++) {
            writeThreads.add(new TestWriteLock(writeLock));
        }

        readThreads.forEach(Thread::start);

        writeThreads.forEach(Thread::start);

        System.out.println(String.format("all threas has been called!"));

        TimeUnit.SECONDS.sleep(20);

    }

}
