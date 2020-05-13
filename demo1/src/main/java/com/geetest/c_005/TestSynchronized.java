package com.geetest.c_005;

/**
 * @author zhoubing
 * @date 2020-04-19 14:53
 */
public class TestSynchronized implements Runnable {

    private int count = 100;

    @Override
    public void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + ":" + count);
    }

    public static void main(String[] args) {
        TestSynchronized testSynchronized = new TestSynchronized();
        for (int i = 0; i < 100; i++) {
            new Thread(testSynchronized, "THREAD-" + i).start();
        }


    }
}
