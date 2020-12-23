package com.beiwu.ch1.wn;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-24 00:34
 */
public class WaitNotifyUse {
    private Object obj = new Object();

    private int num = 10;

    public void call1(){
        System.out.println("call1() try get syn " + obj.hashCode());
        synchronized (obj){
            System.out.println("call1() get syn " + obj.hashCode());
            System.out.println("call1() method start----->");
            while (num < 100){
                try {
                    System.out.println("call1() method wait----->");
                    obj.wait();
                    System.out.println("call1() method wait end----->");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("call1 method end----->");
        }
    }
    public void call2(){
        System.out.println("call2() try get syn " + obj.hashCode());
        synchronized (obj){
            System.out.println("call2() get syn " + obj.hashCode());
            System.out.println("call2 method start----->");
            SleepTools.second(2);
            num = 101;
            System.out.println("call2 method end----->");
            obj.notify();
        }
    }

    public static void main(String[] args) {
        WaitNotifyUse waitNotifyUse = new WaitNotifyUse();
        Thread t1 = new Thread(() -> {
            waitNotifyUse.call1();
        });
        Thread t2 = new Thread(() -> {
            waitNotifyUse.call2();
        });

        t1.start();
        SleepTools.ms(600);
        t2.start();

    }
}
