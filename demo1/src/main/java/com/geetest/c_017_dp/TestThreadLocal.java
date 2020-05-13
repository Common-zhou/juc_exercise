package com.geetest.c_017_dp;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-09 08:28
 */
public class TestThreadLocal {
    static String name = "zhangsan";

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            name = "lisi";
        }).start();

        System.out.println(name);

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
        }).start();

    }
}
