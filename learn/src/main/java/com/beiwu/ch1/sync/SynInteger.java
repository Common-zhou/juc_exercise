package com.beiwu.ch1.sync;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-24 00:00
 * 不能使用Integer做锁 因为每一次自增后 Integer变量就会新建一个
 */
public class SynInteger {
    private Integer integer = new Integer(0);

    public void increase() {
        synchronized (integer) {
            integer++;
            SleepTools.second(3);
            System.out.println(Thread.currentThread().getName() + " : "
                    + integer);
        }
    }

    public static void main(String[] args) {
        SynInteger synInteger = new SynInteger();
        Thread thread1 = new Thread(() -> {
            synInteger.increase();
        });
        Thread thread2 = new Thread(() -> {
            synInteger.increase();
        });
        Thread thread3 = new Thread(() -> {
            synInteger.increase();
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }

}
