package com.geetest.c_020;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhoubing
 * @date 2020-05-13 09:21
 */
public class ConcurrentLinkedQueueTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueue linkedQueue = new ConcurrentLinkedQueue();
        for (int i = 0; i < 10; i++) {
            linkedQueue.offer("a" + i);
        }
        linkedQueue.offer("bbb");
        System.out.println(linkedQueue.size());

        System.out.println(linkedQueue.poll());
        System.out.println(linkedQueue.peek());
        System.out.println(linkedQueue.peek());

        System.out.println("======================");

    }
}
