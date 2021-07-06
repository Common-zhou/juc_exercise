package com.beiwu.learn_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhoubing
 * @Date 2021-07-06 11:32
 */
public class LearnBlockingQueue {

    private static volatile boolean flag = true;
    private static AtomicInteger idGenerator = new AtomicInteger(0);

    private static class Produce extends Thread {
        private BlockingQueue<Integer> queue;

        public Produce(String name, BlockingQueue<Integer> queue) {
            super(name);
            this.queue = queue;
        }

        @Override
        public void run() {
            while (flag) {
                try {
                    int id = idGenerator.getAndIncrement();
                    queue.put(id);
                    System.out.println(String.format("[%s] has put one record: %d", Thread.currentThread().getName(), id));
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer extends Thread {
        private BlockingQueue<Integer> queue;

        public Consumer(String name, BlockingQueue<Integer> queue) {
            super(name);
            this.queue = queue;
        }

        @Override
        public void run() {
            while (flag) {
                try {
                    Integer id = queue.take();
                    System.out.println(String.format("[%s] got one record: %d", Thread.currentThread().getName(), id));
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(20);
        queue.offer(1);

        //normalUse(queue);

        Produce produce = new Produce("Produce-1", queue);
        Produce produce2 = new Produce("Produce-2", queue);
        Consumer consumer = new Consumer("Consume-1", queue);
        Consumer consumer2 = new Consumer("Consume-1", queue);

        produce.start();
        produce2.start();
        consumer.start();
        consumer2.start();

        TimeUnit.SECONDS.sleep(30);

        flag = false;
    }

    private static void normalUse(BlockingQueue<Integer> queue) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            // add/offer 这些都是普通的方法 不会阻塞
            //queue.add(i);
            //queue.offer(i);
            queue.put(i);
        }

        System.out.println("enter 19 line.");
    }

}
