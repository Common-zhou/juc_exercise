package com.beiwu.ch2.tools.semaphore;

import com.beiwu.ch1.tools.SleepTools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * @author zhoubing
 * @date 2020-12-25 12:16
 * Semaphore基本使用 它主要用于限流
 */
public class UseSemaphore {
    private static Semaphore semaphore = new Semaphore(10);

    private static class SemaphoreThread extends Thread {
        @Override
        public void run() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:MM:ss");
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "  got one" + simpleDateFormat.format(new Date()));
                SleepTools.second(1);
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + "  release one" + simpleDateFormat.format(new Date()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            new SemaphoreThread().start();
        }
        System.out.println("Main thread init success......");
    }

}
