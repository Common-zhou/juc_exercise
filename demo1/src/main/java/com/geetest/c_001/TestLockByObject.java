package com.geetest.c_001;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-19 14:28
 */
public class TestLockByObject {

    final static Object object = new Object();

    public static void testLock() {
        synchronized (object) {
            for (int i = 0; i < 100; i++) {
                System.out.println("A" + ": " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 8) {
                    int p = 1 / 0;
                }
            }
        }
    }

    public static void testLock2() {
        synchronized (object) {
            for (int i = 0; i < 100; i++) {
                System.out.println("B" + ": " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestLockByObject testLockByObject = new TestLockByObject();
        new Thread(TestLockByObject::testLock).start();
        new Thread(TestLockByObject::testLock2).start();


        TimeUnit.SECONDS.sleep(100);
    }
}
