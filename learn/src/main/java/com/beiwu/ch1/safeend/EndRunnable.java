package com.beiwu.ch1.safeend;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 12:19
 */
public class EndRunnable {
    private static class UserRunnable implements Runnable{
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" interrrupt flag ="+Thread.currentThread().isInterrupted());

            while (!Thread.currentThread().isInterrupted()){
                System.out.println(threadName+" interrrupt inner flag ="+Thread.currentThread().isInterrupted());
            }

            System.out.println(threadName+" interrrupt flag ="+Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new UserRunnable());
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
