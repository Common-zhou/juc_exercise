package com.beiwu.concurrent.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类说明：
 */
public class ExpressCond {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/
    //TODO
    private Lock lock = new ReentrantLock();
    private Condition kmCondition = lock.newCondition();
    private Condition siteCondition = lock.newCondition();

    public ExpressCond() {
    }

    public ExpressCond(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public void changeKm() {
        this.km += 30;
        //TODO
        lock.lock();
        System.out.println("km change");

        try {
            kmCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public void changeSite(String site) {
        this.site = site;
        lock.lock();
        System.out.println("site change");

        try {
            siteCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /*当快递的里程数大于100时更新数据库*/
    public void waitKm() {
        lock.lock();

        try {
            while (this.km < 100) {
                kmCondition.await();
                System.out.println(String.format("km changed , the new val: %d", this.km));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        System.out.println("the Km is " + this.km + ",I will change db");
    }

    /*当快递到达目的地时通知用户*/
    public void waitSite() {
        lock.lock();

        try {
            while (!CITY.equals(this.site)) {
                // 如果没有到 那就继续等待
                siteCondition.await();
                System.out.println(String.format("the site has changed, the new val: %s", this.site));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


        System.out.println("the site is " + this.site + ",I will call user");
    }
}
