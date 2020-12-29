package com.beiwu.ch4.aqs;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhoubing
 * @date 2020-12-29 10:36
 * 允许三个线程进入的锁
 */
public class TrinityLock implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {

        public Sync(int count) {
            if (count <= 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(count);
        }

        /**
         * @param reduceCount 扣减个数
         * @return 返回小于0 则代表当前线程获取同步状态失败
         * 大于0 代表当前线程获得同步状态成功
         */
        @Override
        protected int tryAcquireShared(int reduceCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current - reduceCount;
                if (newCount < 0 || compareAndSetState(newCount, current)) {
                    return newCount;
                }

            }
        }

        /**
         * @param returnCount 返还状态个数
         * @return
         */
        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for (; ; ) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current, newCount)) {
                    return true;
                }
            }
        }
    }

    private Sync sync = new Sync(3);

    @Override
    public void lock() {
        sync.tryAcquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.tryReleaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    public static void main(String[] args) {
        TrinityLock lock = new TrinityLock();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " try get lock......");
                lock.lock();

                try {
                    SleepTools.second(1);
                    System.out.println(Thread.currentThread().getName() + " got lock......");
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " released lock......");
                }
            }).start();
        }
        System.out.println("the init thread has inited success");
    }
}
