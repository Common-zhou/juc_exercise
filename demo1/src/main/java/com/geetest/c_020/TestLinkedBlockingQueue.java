package com.geetest.c_020;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-13 09:44
 */
public class TestLinkedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>(1000);

//        for (int i = 0; i < 10; i++) {
//            blockingQueue.put("a" + i);
//        }
//
//        System.out.println("start");
//        blockingQueue.put("aaa");
//        System.out.println("end");
//        System.out.println(blockingQueue.size());

        Thread[] produceThreads = new Thread[10];
        for (int i = 0; i < produceThreads.length; i++) {
            produceThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        blockingQueue.put(Thread.currentThread().getName() + "-" + j);
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        Thread[] consumeThreads = new Thread[10];
        for (int i = 0; i < consumeThreads.length; i++) {
            consumeThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    while (true) {
                        try {
                            System.out.println(blockingQueue.take());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        for (Thread consumeThread : consumeThreads) {
            consumeThread.start();
        }

        for (Thread produceThread : produceThreads) {
            produceThread.start();
        }


    }
}
