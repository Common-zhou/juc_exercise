package com.geetest.c_016_gc;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-07 17:33
 */
public class TestSingleton2 {
    private static TestSingleton2 instance;

    private TestSingleton2() {
    }

    static TestSingleton2 getTestSingleton() {
        if (instance == null) {
            synchronized (TestSingleton2.class) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("sleep");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new TestSingleton2();
            }
        }
        return instance;
    }
}
