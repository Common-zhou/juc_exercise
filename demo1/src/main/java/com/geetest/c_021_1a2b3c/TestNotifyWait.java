package com.geetest.c_021_1a2b3c;

/**
 * @author zhoubing
 * @date 2020-05-13 14:54
 */
public class TestNotifyWait {
    public static void main(String[] args) {
        Object o = new Object();

        char[] numChars = "123456".toCharArray();
        char[] alphtChars = "ABCDEF".toCharArray();

        new Thread(() -> {
            synchronized (o) {
                for (char c : numChars) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (o) {
                for (char c : alphtChars) {
                    System.out.println(c);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                o.notify();
            }
        }).start();


    }
}
