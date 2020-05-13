package com.geetest.c_021_1a2b3c;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zhoubing
 * @date 2020-05-13 11:09
 */
public class TestLockSupport {
    private static Thread thread1;
    private static Thread thread2;
    public static void main(String[] args) throws InterruptedException {
        String number = "12345";
        String alpha = "ABCDE";

        thread1 = new Thread(() -> {
            for (int i = 0; i < number.length(); i++) {
                System.out.println(number.charAt(i));
                LockSupport.unpark(thread2);
                LockSupport.park();
            }
        });

        thread2 = new Thread(() -> {
            for (int i = 0; i < alpha.length(); i++) {
                LockSupport.park();
                System.out.println(alpha.charAt(i));
                LockSupport.unpark(thread1);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }
}
