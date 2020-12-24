package com.beiwu.ch1.vola;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-24 09:57
 * 测试
 */
public class UseVolatile {

//    private volatile boolean flag = false;
    private static boolean flag = false;

    public static void circule(){
        while (!flag){
            System.out.println(Thread.currentThread().getName() + ":loop once------");
            SleepTools.ms(5);
        }
        System.out.println(Thread.currentThread().getName() + ": end loop---------------");
    }


    public static void main(String[] args) {
        UseVolatile useVolatile = new UseVolatile();
        Thread thread = new Thread(() -> {
            circule();
        });
        Thread thread2 = new Thread(() -> {
            circule();
        });
        thread.start();
        thread2.start();

        SleepTools.second(1);
        flag = true;
    }
}
