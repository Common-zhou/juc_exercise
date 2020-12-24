package com.beiwu.ch1.wn;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-24 10:24
 */
public class ProduceAndConsumer {
    private int produceAndConsumeNum = 10;
    private Object obj = new Object();

    public void produce() {
        int producedNum = 0;
        System.out.println(Thread.currentThread().getName() + " : 生产者开始生产包子--------");

        synchronized (obj) {
            while (producedNum < produceAndConsumeNum) {
                producedNum++;
                System.out.println(Thread.currentThread().getName() + " : 生产者生产了第" + producedNum + "个包子--------");
                try {
                    obj.notify();
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            obj.notify();
        }

        System.out.println(" 生产者end-----");

    }

    public void consume() {
        int consumedNum = 0;
        System.out.println(Thread.currentThread().getName() + " : 消费者开始消费包子--------");

        synchronized (obj) {
            while (consumedNum < produceAndConsumeNum) {
                consumedNum++;
                System.out.println(Thread.currentThread().getName() + " : 消费者开始消费了第" + consumedNum + "个包子--------");
                try {
                    obj.notify();
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(" 消费者end-----");
    }

    public static void main(String[] args) {
        ProduceAndConsumer holder = new ProduceAndConsumer();
        Thread producer = new Thread(() -> {
            holder.produce();
        });
        Thread consumer = new Thread(() -> {
            holder.consume();
        });

        producer.start();
        SleepTools.second(1);
        consumer.start();

    }
}
