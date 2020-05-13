package com.geetest.c_016_gc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-07 16:11
 */
public class TestAnswer9 {
    public static void main(String[] args) {
        //9：写一个程序，在main线程中启动100个线程，100个线程完成后，主线程打印“完成”，
        // 使用join()和countdownlatch都可以完成，请比较异同。
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        }

        threadList.forEach(Thread::start);
        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("完成");
    }
}
