package com.beiwu.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * @author zhoubing
 * @date 2021-01-04 23:36
 */
public class PutOrder implements Runnable {
    private DelayQueue<ItemVo<Order>> queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Order orderTb = new Order("Tb12345", 366,
                System.currentTimeMillis());
        ItemVo<Order> itemTb = new ItemVo<>(5, orderTb);
        queue.offer(itemTb);
        System.out.println("订单5秒后超时:" + orderTb.getOrderNo() + ";"
                + orderTb.getOrderMoney());

        //8s 后到期
        Order orderJd = new Order("Jd54321", 322,
                System.currentTimeMillis());
        ItemVo<Order> itemJd = new ItemVo<>(8, orderJd);
        queue.offer(itemJd);
        System.out.println("订单8秒后超时:" + orderJd.getOrderNo() + ";"
                + orderJd.getOrderMoney());
    }
}
