package com.beiwu.ch4.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author zhoubing
 * @date 2020-12-29 10:16
 * 自己实现的简易可重入锁
 */
public class SelfReentrantLock implements Lock {
    private Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                // 拿到锁了
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            if (getExclusiveOwnerThread() == Thread.currentThread()) {
                //当前线程是获取独占的锁
                setState(getState() + 1);
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setState(getState() - 1);
            if (getState() == 0) {
                setExclusiveOwnerThread(null);
            }
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState() > 0;
        }
    }

    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName() + " try get lock......");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName() + " got lock......");
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
        System.out.println(Thread.currentThread().getName() + " try release lock......");
        sync.release(1);
        System.out.println(Thread.currentThread().getName() + " released lock......");
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
