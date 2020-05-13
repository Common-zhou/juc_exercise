package com.geetest.c_017_dp;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zhoubing
 * @date 2020-05-12 19:18
 */
public class HelloQueue {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        queue.add(2);
        queue.add(10);
        queue.add(13);

        System.out.println("end");
    }
}
