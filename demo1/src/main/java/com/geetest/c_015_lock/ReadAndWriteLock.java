package com.geetest.c_015_lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhoubing
 * @date 2020-04-22 18:09
 */
public class ReadAndWriteLock {
    static ReadAndWriteLock readAndWriteLock = new ReadAndWriteLock();

    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    void readMethod(Lock lock){
        lock.lock();
        try {
            //模拟读的时间
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("read success");
            lock.unlock();
        }
    }

    void writeMethod(Lock lock){
        lock.lock();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("write success");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        CountDownLatch countDownLatch = new CountDownLatch(threads.length);
        Lock lock = new ReentrantLock();
        for (int i = 0; i < threads.length; i++) {
            if (i % 4 == 0){
                threads[i] = new Thread(()->{
                    readAndWriteLock.writeMethod(lock);
                    countDownLatch.countDown();
                });
            }else{
                threads[i] = new Thread(()->{
                    readAndWriteLock.readMethod(lock);
                    countDownLatch.countDown();
                });
            }
        }

        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("normal lock consume: " + (System.currentTimeMillis() - startTime) + " ms");

        System.out.println("=========================================");
        CountDownLatch count = new CountDownLatch(threads.length);

        for (int i = 0; i < threads.length; i++) {
            if (i % 4 == 0){
                threads[i] = new Thread(()->{
                    readAndWriteLock.writeMethod(writeLock);
                    count.countDown();
                });
            }else{
                threads[i] = new Thread(()->{
                    readAndWriteLock.readMethod(readLock);
                    count.countDown();
                });
            }
        }

        startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("readAndWrite lock consume: " + (System.currentTimeMillis() - startTime) + " ms");

    }
}
