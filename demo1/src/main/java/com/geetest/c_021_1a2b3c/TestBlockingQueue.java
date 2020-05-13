package com.geetest.c_021_1a2b3c;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author zhoubing
 * @date 2020-05-13 12:13
 */
public class TestBlockingQueue {
    BlockingQueue t1 = new ArrayBlockingQueue(1);
    BlockingQueue t2 = new ArrayBlockingQueue(1);
    public static void main(String[] args) {

        char[] numberChars = "123456".toCharArray();
        char[] alphaChars = "ABCDEF".toCharArray();

        new Thread(()->{
            for (char c : numberChars) {
                System.out.println(c);

            }
        }).start();
    }
}
