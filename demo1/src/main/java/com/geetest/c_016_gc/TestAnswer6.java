package com.geetest.c_016_gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2020-05-07 16:16
 */
public class TestAnswer6 {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    static CountDownLatch countDownLatch = new CountDownLatch(100);

    void method1() {
        int oldNum = atomicInteger.get();

        atomicInteger.set(oldNum + 1);
    }

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();

        TestAnswer6 testAnswer6 = new TestAnswer6();

        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(() -> {
                testAnswer6.method1();
                countDownLatch.countDown();
            }));
        }

        threadList.forEach(Thread::start);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("线程执行完成");
        System.out.println(testAnswer6.atomicInteger.get());

    }
}
