package com.beiwu.ch1.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 23:10
 * 将锁放在方法上 或者是静态方法上
 *
 * 锁放在方法上 锁的对象是当前实例
 * 如果是放在静态代码块上 则锁的对象是class字节码
 */
public class SynchronizedMethod {
    public synchronized void increase() {
        System.out.println("increase enter------>>");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("increase leave------>>");
    }

    public synchronized void decrease() {
        System.out.println("decrease enter------>>");

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("decrease leave------>>");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethod synchronizedMethod = new SynchronizedMethod();
        new Thread(()->{
            synchronizedMethod.increase();
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            synchronizedMethod.decrease();
        }).start();

        System.out.println("main method over......");
    }

}
