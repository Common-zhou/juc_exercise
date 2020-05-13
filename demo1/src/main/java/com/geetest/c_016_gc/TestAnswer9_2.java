package com.geetest.c_016_gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-07 16:13
 */
public class TestAnswer9_2 {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }));
        }

        threadList.forEach(Thread::start);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("完成");

    }
}
