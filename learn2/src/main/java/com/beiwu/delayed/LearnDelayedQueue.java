package com.beiwu.delayed;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * 学习DelayedQueue
 *
 * @Author zhoubing
 * @Date 2021-07-05 16:40
 */
public class LearnDelayedQueue {

    private static class FetchDelayedQueue implements Runnable {

        private DelayQueue<CusItemVo<String>> delayQueue;

        public FetchDelayedQueue(DelayQueue<CusItemVo<String>> delayQueue) {
            this.delayQueue = delayQueue;
        }

        @Override
        public void run() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (true) {
                try {
                    CusItemVo<String> item = delayQueue.take();
                    if (item == null) {
                        System.out.println("got one record null.");
                        continue;
                    }
                    Date date = new Date(item.getExpireDate());
                    System.out.println(String.format("got one record [%s] from delayQueue;delay time:[%s]", item.getData(), dateFormat.format(date)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        DelayQueue<CusItemVo<String>> delayQueue = new DelayQueue<>();

        Thread t = new Thread(new FetchDelayedQueue(delayQueue));
        t.start();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int expire = random.nextInt(10);
            delayQueue.add(new CusItemVo("test-" + i, expire));
        }

    }
}
