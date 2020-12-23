package com.beiwu.ch1;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 12:02
 */
public class DaemonThreadTest {
    private static class DaemonThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println("enter DaemonThread------>>>>>");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //守护线程中finally不一定起作用
                System.out.println("finally DaemonThread----");
            }
            System.out.println("out DaemonThread>>>>>------");
        }
    }
    public static void main(String[] args) throws InterruptedException {
        DaemonThread thread = new DaemonThread();

        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(2);


    }
}
