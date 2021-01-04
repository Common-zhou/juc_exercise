package com.beiwu.ch5.bq;

/**
 * @author zhoubing
 * @date 2021-01-04 23:35
 */
public class Order {
    //订单的编号
    private final String orderNo;
    // 订单的金额
    private final double orderMoney;

    private long startTime;

    public Order(String orderNo, double orderMoney, long startTime) {
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
        this.startTime = startTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public long getStartTime() {
        return startTime;
    }
}
