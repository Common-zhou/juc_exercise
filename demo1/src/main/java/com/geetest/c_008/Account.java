package com.geetest.c_008;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-19 15:17
 */
public class Account {
    private String username;
    private BigDecimal balance = new BigDecimal(0);

    public synchronized void set(String username, BigDecimal balance){
        this.username = username;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized BigDecimal getBalance(String username){
        return this.balance;
    }

    public static void main(String[] args) throws InterruptedException {
        Account account = new Account();

        Thread thread = new Thread(() -> {
            account.set("zhangsan", new BigDecimal(100));
        });
        thread.start();

        try {
            TimeUnit.MICROSECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("zhangsan").doubleValue());

        thread.join();

        System.out.println(account.getBalance("zhangsan").doubleValue());
    }
}
