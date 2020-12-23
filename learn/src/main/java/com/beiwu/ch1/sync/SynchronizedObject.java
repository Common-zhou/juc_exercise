package com.beiwu.ch1.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 22:57
 *
 * 将外部的一个对象作为锁
 */
public class SynchronizedObject {
    // 对象可以作为一个锁
    private final Object obj = new Object();

    public void increase() {
        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + " increase " + ": " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void decrease() {
        synchronized (obj) {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread().getName() + " decrease " + ": " + i);
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedObject object = new SynchronizedObject();
        new Thread(() -> {
            object.increase();
        }).start();

        new Thread(() -> {
            object.decrease();
        }).start();

    }
}
