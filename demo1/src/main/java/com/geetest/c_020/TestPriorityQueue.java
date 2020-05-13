package com.geetest.c_020;

import java.util.PriorityQueue;

/**
 * @author zhoubing
 * @date 2020-05-13 10:07
 */
public class TestPriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.add("a");
        priorityQueue.add("d");
        priorityQueue.add("c");
        priorityQueue.add("z");
        priorityQueue.add("q");
        priorityQueue.add("y");
        for (int i = 0; i < 6; i++) {
            System.out.println(priorityQueue.poll());
        }
    }
}
