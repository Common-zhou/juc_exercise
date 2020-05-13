package com.geetest.c_021_1a2b3c;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2020-05-13 14:39
 */
public class TestAtomicInteger {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {

        char[] numChars = "123456".toCharArray();
        char[] alphaChars = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (char c : numChars) {
                while (atomicInteger.get() != 1) {
                }
                System.out.println(c);
                atomicInteger.set(2);
            }
        }).start();

        new Thread(() -> {
            for (char c : alphaChars) {
                while (atomicInteger.get() != 2) {
                }
                System.out.println(c);
                atomicInteger.set(1);
            }
        }).start();

    }
}
