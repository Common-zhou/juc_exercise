package com.geetest.c_018_fromHashtableToCHM;

import java.util.Hashtable;
import java.util.UUID;

/**
 * @author zhoubing
 * @date 2020-05-12 19:26
 */
public class TestHashtable {
    static Hashtable<UUID, UUID> m = new Hashtable<>();

    static final int count = Constants.COUNT;
    static final int threadCount = Constants.THREAD_COUNT;

    static UUID[] keys = new UUID[count];
    static UUID[] values = new UUID[count];

    static {
        for (int i = 0; i < count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        int start;
        int gap = count / threadCount;

        public MyThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + gap; i++) {
                m.put(keys[i], values[i]);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new MyThread(i * (count / threadCount));
        }

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

        System.out.println("Hashtable   consume time:" + (System.currentTimeMillis() - start));

        System.out.println(m.size());

        System.out.println("===========================================");

        start = System.currentTimeMillis();

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                m.get(keys[10]);
            });
        }
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

        System.out.println("Hashtable  get 10000*100  consume time:" + (System.currentTimeMillis() - start));

    }
}
