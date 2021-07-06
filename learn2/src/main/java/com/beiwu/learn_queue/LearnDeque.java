package com.beiwu.learn_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author zhoubing
 * @Date 2021-07-06 09:43
 */
public class LearnDeque {
    public static void main(String[] args) {
        //Deque<Integer> deque = new LinkedList<>();
        Deque<Integer> deque = new ArrayDeque<>(5);


        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addFirst(4);

        for (int i = 0; i < 10; i++) {
            // remove 会报错。
            //Integer poll = deque.remove();
            Integer poll = deque.poll();
            System.out.println(poll);
        }

        // element也会抛出异常
        //System.out.println(deque.element());
        System.out.println(deque.peek());

    }

}
