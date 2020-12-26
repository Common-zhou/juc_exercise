package com.beiwu.ch4.rwl;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhoubing
 * @date 2020-12-27 00:54
 */
public class UseRwLock implements GoodService {

    private GoodsInfo goodsInfo;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    public UseRwLock(GoodsInfo goodsInfo){
        this.goodsInfo = goodsInfo;
    }

    @Override
    public void setNumber(int number) {
        writeLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public GoodsInfo getNumber() {
        readLock.lock();

        try {
            SleepTools.ms(5);
            return goodsInfo;
        } finally {
            readLock.unlock();
        }
    }
}
