package com.beiwu.ch3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2020-12-26 16:20
 */
public class UseAtomicInteger {
    private static AtomicInteger count = new AtomicInteger(0);
    //private static int count = 0;

    private void increse(){
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
            //count++;
        }
        System.out.println(Thread.currentThread().getName() + " complete work..., result=" + count.get());
        //System.out.println(Thread.currentThread().getName() + " complete work..., result=" + count);
    }

    public static void main(String[] args) {
        UseAtomicInteger methodHolder = new UseAtomicInteger();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                methodHolder.increse();
            }).start();
        }

        System.out.println("Main thread init success......");
    }
}
