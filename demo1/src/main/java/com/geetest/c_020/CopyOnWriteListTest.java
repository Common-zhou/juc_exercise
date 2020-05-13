package com.geetest.c_020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-05-13 09:06
 */
public class CopyOnWriteListTest {
    public static void main(String[] args) {
//        List<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new ArrayList<>();

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    list.add("a" + j);
                }
            });
        }

        runAndComputeTime(threads);

        System.out.println(list.size());
    }

    static void runAndComputeTime(Thread[] threads) {
        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("consume time:" + (System.currentTimeMillis() - startTime));
    }
}
