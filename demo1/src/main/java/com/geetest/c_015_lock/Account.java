package com.geetest.c_015_lock;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-27 22:14
 */
public class Account {
    private String name;
    private double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance(String name) {
        return this.balance;
    }

    public static void main(String[] args) throws InterruptedException {
        Account accountZhangsan = new Account();
        Account accountLisi = new Account();

        new Thread(()->{
            accountZhangsan.set("zhangsan", 66.66);
        }).start();

        new Thread(()->{
            System.out.println(accountZhangsan.getBalance("zhangsan"));
        }).start();

        TimeUnit.SECONDS.sleep(2);


    }
}
