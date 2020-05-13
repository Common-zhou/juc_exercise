package com.geetest.c_012;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-21 21:42
 */
public class SyncChange2 {
    Object object = new Object();

    void method(){
        synchronized (object){
            System.out.println(Thread.currentThread().getName() + "  started...");
            while (true){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncChange2 syncChange2 = new SyncChange2();

        Thread thread1 = new Thread(syncChange2::method);
        Thread thread2 = new Thread(syncChange2::method);
        Thread thread3 = new Thread(syncChange2::method);

        thread1.start();

        TimeUnit.SECONDS.sleep(5);

        syncChange2.object = new Object();

        thread2.start();
    }
}
