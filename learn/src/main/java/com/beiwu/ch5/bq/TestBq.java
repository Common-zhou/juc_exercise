package com.beiwu.ch5.bq;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2021-01-04 21:56
 * bq常见api练习
 */
public class TestBq {
    private static BlockingQueue<Ticket> ticketBlockingQueue = new ArrayBlockingQueue<>(20);
    private static AtomicInteger count = new AtomicInteger(0);

    private static class ConsumerThread extends Thread {
        @Override
        public void run() {
            // 当前线程没有被中断
            while (!Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + " consume thread begin consume>>>>>>");

                try {
                    if (ticketBlockingQueue.size() == 0){
                        System.out.println("当前阻塞队列已经空了-----");
                    }
                    Ticket ticket = ticketBlockingQueue.take();
                    System.out.println("consume one ticket " + ticket.getTickId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SleepTools.ms(100);
            }
        }
    }

    private static class ProduceThread extends Thread {
        @Override
        public void run() {

            // 当前线程没有被中断
            while (!Thread.interrupted()) {
                //System.out.println(Thread.currentThread().getName() + " produce thread begin produce>>>>>>");
                int id = count.getAndIncrement();

                Ticket ticket = new Ticket("thread_" + id, id);
                try {
                    if (ticketBlockingQueue.size() == 20){
                        System.out.println("当前阻塞队列已经满了-----");
                    }
                    ticketBlockingQueue.put(ticket);
                    System.out.println(Thread.currentThread().getName() + " produced one thread ----" + ticket.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SleepTools.ms(10);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            ProduceThread thread = new ProduceThread();
            thread.start();
        }

        for (int i = 0; i < 20; i++) {
            ConsumerThread thread = new ConsumerThread();
            thread.start();
        }


    }
}
