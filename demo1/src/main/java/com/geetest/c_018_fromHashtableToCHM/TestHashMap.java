package com.geetest.c_018_fromHashtableToCHM;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoubing
 * @date 2020-05-12 23:53
 */
public class TestHashMap {
    private static final int THREAD_NUMBER = Constants.THREAD_COUNT;
    private static final int COUNT = Constants.COUNT;
    static Thread[] threads = new Thread[THREAD_NUMBER];
    static Map<UUID, UUID> m = new HashMap<>();

    private static UUID[] keys;
    private static UUID[] values;

    static {
        keys = new UUID[COUNT];
        values = new UUID[COUNT];

        for (int i = 0; i < COUNT; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }

    static class MyThread extends Thread {
        private int start;
        private int gap = COUNT / THREAD_NUMBER;

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


    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        for (int i = 0; i < THREAD_NUMBER; i++) {
            threads[i] = new MyThread(i * (COUNT / THREAD_NUMBER));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("insert time:" + (System.currentTimeMillis() - start));
        System.out.println(m.size());
        new ConcurrentHashMap<>();
    }
}
