package com.beiwu.ch1;

/**
 * @author zhoubing
 * @date 2020-12-23 11:29
 */
public class NewThread2Ways {
    public static void main(String[] args) {
        CustomThread thread1 = new CustomThread();
        Thread thread2 = new Thread(new CustomRunnable());

        thread1.start();
        thread2.start();
        thread2.start();
    }
}
