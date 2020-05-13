package com.geetest.c_020;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @author zhoubing
 * @date 2020-05-13 11:04
 */
public class TestTransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> transferQueue = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(transferQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        transferQueue.transfer("hello");
        System.out.println("put end");
    }
}
