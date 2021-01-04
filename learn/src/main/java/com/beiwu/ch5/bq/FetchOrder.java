package com.beiwu.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * @author zhoubing
 * @date 2021-01-04 23:51
 */
public class FetchOrder implements Runnable {
    private DelayQueue<ItemVo<Order>> queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                ItemVo<Order> item = queue.take();
                Order order = item.getData();
                System.out.println("Get From Queue:" + "data="
                        + order.getOrderNo() + ";" + order.getOrderMoney());

                System.out.println("consume time:" + (System.currentTimeMillis() - order.getStartTime()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
