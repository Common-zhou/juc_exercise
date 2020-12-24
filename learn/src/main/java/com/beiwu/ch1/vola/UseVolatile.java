package com.beiwu.ch1.vola;

/**
 * @author zhoubing
 * @date 2020-12-24 09:57
 * 测试volatile
 *  打印语句会上锁  会刷新内存
 *  sleep也会刷新内存
 *  Thread.currentThread().getName();也会刷新
 */

public class UseVolatile {

//    public volatile static boolean flag = false;
    public static boolean flag = false;

    public static void circule() throws InterruptedException {
        while (!flag) {
//            System.out.println(Thread.currentThread().getName() + ":loop once------");
//            System.out.println("loop once------");
//            Thread.sleep(5);
//            Thread.currentThread().getName();
        }
        System.out.println(Thread.currentThread().getName() + ": end loop---------------");
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                UseVolatile.circule();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });

        thread.start();

        Thread.sleep(2000);
        System.out.println(Thread.currentThread()+"更改值");
        UseVolatile.flag = true;
    }
}
