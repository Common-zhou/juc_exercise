package com.geetest.c_007;

import java.util.concurrent.TimeUnit;

/**
 * 同步和非同步方法是否可以同时调用
 * @author zhoubing
 * @date 2020-04-19 15:00
 */
public class TestBoth {

    public synchronized void method1(){
        System.out.println("method1 started");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2 end");
    }

    public void method2(){
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2");
    }

    public static void main(String[] args) {
        TestBoth testBoth = new TestBoth();
        new Thread(testBoth::method1, "t1").start();
        new Thread(testBoth::method2, "t2").start();


    }
}
