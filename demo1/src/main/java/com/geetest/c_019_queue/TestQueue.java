package com.geetest.c_019_queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-13 08:36
 */
public class TestQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            queue.add("add " + i);
        }

//        queue.add("hello");

        boolean hello = queue.offer("hello");

        boolean world = queue.offer("world", 2, TimeUnit.SECONDS);
        System.out.println(hello);
        System.out.println(world);

        System.out.println(queue.size());


        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);
        String peek = blockingQueue.peek();
        System.out.println(peek);
    }
}
