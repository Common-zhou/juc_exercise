package com.geetest.c_016_gc;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-07 17:33
 */
public class TestSingleton3 {
    private static TestSingleton3 instance;

    private TestSingleton3() {
    }

    static TestSingleton3 getTestSingleton() {
        if (instance == null) {
            synchronized (TestSingleton3.class) {
                if (instance == null) {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println("sleep");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new TestSingleton3();
                }
            }
        }
        return instance;
    }
}
