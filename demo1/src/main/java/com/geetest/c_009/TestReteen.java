package com.geetest.c_009;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-19 15:37
 */
public class TestReteen {

    public synchronized void method1(){
        System.out.println("method1 start");

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        method2();

        System.out.println("method1 end");
    }

    public synchronized void method2(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method2");
    }

    public static void main(String[] args) {
        TestReteen testReteen = new TestReteen();

        new Thread(testReteen::method1, "t1").start();
    }
}
