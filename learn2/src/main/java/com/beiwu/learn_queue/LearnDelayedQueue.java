package com.beiwu.learn_queue;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * 学习延时队列的使用。
 * 主要关键在于延时队列的Item编写
 * @Author zhoubing
 * @Date 2021-07-06 14:00
 */
public class LearnDelayedQueue {
    private static final DelayQueue<ItemVO<Integer>> queue = new DelayQueue<>();

    private static class FetchJob implements Runnable {
        private DelayQueue<ItemVO<Integer>> delayQueue;

        public FetchJob(DelayQueue<ItemVO<Integer>> delayQueue) {
            this.delayQueue = delayQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    ItemVO<Integer> item = delayQueue.take();
                    System.out.println(String.format("[%s] has got one record:%d", Thread.currentThread().getName(), item.getData()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    static {
        Thread t = new Thread(new FetchJob(queue));
        t.setDaemon(true);
        t.start();
    }

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int delayedTime = random.nextInt(30);
            System.out.println(String.format("%d wait %d seconds.", i, delayedTime));
            ItemVO<Integer> e = new ItemVO<>(delayedTime, i);
            queue.put(e);
        }

        TimeUnit.SECONDS.sleep(30);

    }
}
