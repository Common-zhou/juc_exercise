package com.beiwu.ch2.tools;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * @author zhoubing
 * @date 2020-12-25 10:13
 * 使用CountDownLatch
 * 共有四个线程来完成countDown 有一个线程会countDown两次
 */
public class UseCountDownLatch {
    static CountDownLatch latch = new CountDownLatch(5);
    private static class CountDownThread extends Thread{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " enter");
            SleepTools.second(1);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " success");
        }
    }

    private static class CountDownRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " enter");
            SleepTools.second(1);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " success");
        }
    }

    public static void main(String[] args) {
        System.out.println("Main thread start......");
        for (int i = 0; i < 3; i++) {
//            CountDownThread thread = new CountDownThread();
//            thread.start();

            Thread thread = new Thread(new CountDownRunnable());
            thread.start();

        }
        System.out.println("Main thread has init 3 jobs......");

        new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " begin do somework1......");
            SleepTools.ms(5);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " done somework1......");

            System.out.println(Thread.currentThread().getName() + " begin do somework2......");
            SleepTools.ms(5);
            latch.countDown();
            System.out.println(Thread.currentThread().getName() + " done somework2......");
        }).start();

        System.out.println("Main Thread has success active other thead job");
        try {
            System.out.println("Main Thread begin wait");
            latch.await();
            System.out.println("Main Thread end wait");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread has done...");


    }
}
