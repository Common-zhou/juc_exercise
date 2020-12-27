package com.beiwu.ch4.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-12-27 15:19
 */
public class Express {
    private Lock lock = new ReentrantLock();
    private Condition siteCondition = lock.newCondition();
    private Condition kmCondition = lock.newCondition();

    private String site;
    private int km;

    public String getSite() {
        return site;
    }

    public int getKm() {
        return km;
    }

    public Express(String site, int km) {
        this.site = site;
        this.km = km;
    }

    public void changeKm(int km) {
        lock.lock();
        try {
            this.km = km;
            kmCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void changeSite(String site) {
        lock.lock();
        try {
            this.site = site;
            siteCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    public void watchKm() {
        System.out.println("begin watch km......");
        lock.lock();
        try {
            while (this.km < 100) {
                try {
                    kmCondition.await();
                    System.out.println("km changed, It's will write to db......" + this.km);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("km > 100, km=" + this.km);
    }

    public void watchSite() {
        System.out.println("begin watch site......");
        lock.lock();

        try {
            while (!"beijing".equalsIgnoreCase(this.site)) {
                try {
                    siteCondition.await();
                    System.out.println("site changed, It's will write to db......" + this.site);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
        System.out.println("site=" + this.site);

    }

}
