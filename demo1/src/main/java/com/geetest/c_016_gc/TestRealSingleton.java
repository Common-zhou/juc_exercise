package com.geetest.c_016_gc;

/**
 * @author zhoubing
 * @date 2020-05-07 17:36
 */
public class TestRealSingleton {
    public static void main(String[] args) {


        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                System.out.println(TestSingleton3.getTestSingleton().hashCode());
            }).start();
            System.out.println(i);
        }
    }
}
