package com.beiwu;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoubing
 * @date 2021-06-25 18:16
 */
public class TestSingle {
    static class HungrySingle {
        // 饿汉模式
        private static HungrySingle hungrySingle = new HungrySingle();

        private HungrySingle() {

        }

        public static HungrySingle getInstance() {
            return hungrySingle;
        }
    }

    static class FullSingle {
        private volatile static FullSingle fullSingle;

        private FullSingle() {
        }

        public static FullSingle getInstance() {
            if (fullSingle == null) {
                synchronized (FullSingle.class) {
                    if (fullSingle == null) {
                        fullSingle = new FullSingle();
                    }
                }
            }
            return fullSingle;
        }

    }

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(10000);

        HashSet<String> set = new HashSet<>();

        int totalSize = 10000;
        CountDownLatch latch = new CountDownLatch(totalSize);

        for (int i = 0; i < totalSize; i++) {
            service.execute(() -> {
                FullSingle single = FullSingle.getInstance();
                set.add(single.toString());
                latch.countDown();
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();

        System.out.println(set.size());


    }
}
