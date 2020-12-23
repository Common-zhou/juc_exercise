package com.beiwu.ch1.safeend;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 12:19
 */
public class EndThread {
    private static class UserThread extends Thread {
        public UserThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " interrrupt flag =" + isInterrupted());
            System.out.println();
            while (!isInterrupted()) {
                System.out.println(threadName + " is running");
                System.out.println(threadName + " interrrupt inner flag =" + isInterrupted());
            }
            System.out.println(threadName + " interrrupt flag =" + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UserThread thread = new UserThread("testThread1");

        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();

    }
}
