package com.beiwu.learn_queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author zhoubing
 * @Date 2021-07-05 17:50
 */
public class LearnQueue {

    public static void main(String[] args) {

        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println(queue.remove());

        queue.add(9);
        queue.add(8);
        queue.add(7);


        System.out.println(queue.remove());
        System.out.println(queue.remove());


    }
}
