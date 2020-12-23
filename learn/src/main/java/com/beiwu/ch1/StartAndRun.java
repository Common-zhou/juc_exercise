package com.beiwu.ch1;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 11:48
 * start 是启动一个新线程去执行它
 * run直接执行 相当于一个普通方法
 */
public class StartAndRun {
    private static class CustomThread2 extends Thread {
        @Override
        public void run() {
            int i = 90;
            while (i > 0) {
                try {
                    TimeUnit.MICROSECONDS.sleep(100);
                    System.out.println("i am " + Thread.currentThread().getName() + ", now i = " + i);
                    i--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        CustomThread2 thread1 = new CustomThread2();
        thread1.setName("threadName");

        thread1.start();
//        thread1.run();

    }
}
