package com.geetest.c_020;

import java.util.concurrent.SynchronousQueue;

/**
 * @author zhoubing
 * @date 2020-05-13 10:57
 */
public class TestSynchronousQueue {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                String str = synchronousQueue.take();
                System.out.println(str);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        synchronousQueue.put("hello world");
//        synchronousQueue.put("hhh");
        synchronousQueue.add("hhh");
        System.out.println(synchronousQueue.size());

    }
}
