package com.beiwu.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * @author zhoubing
 * @date 2021-01-04 23:56
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<ItemVo<Order>> queue = new DelayQueue<>();

        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();

        for (int i = 0; i < 15; i++) {
            Thread.sleep(500);
            System.out.println(i * 500);
        }
    }
}
