package com.geetest.c_021_1a2b3c;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhoubing
 * @date 2020-05-13 12:13
 */
public class TestBlockingQueue {
    static BlockingQueue<String> t1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> t2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {

        char[] numberChars = "123456".toCharArray();
        char[] alphaChars = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (char c : numberChars) {
                System.out.println(c);
                try {
                    t2.put("ok");
                    t1.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t1").start();

        new Thread(() -> {
            for (char c : alphaChars) {
                try {
                    t2.take();
                    System.out.println(c);
                    t1.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, "t2").start();
    }
}
