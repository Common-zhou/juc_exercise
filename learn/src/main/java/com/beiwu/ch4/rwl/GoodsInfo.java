package com.beiwu.ch4.rwl;

/**
 * @author zhoubing
 * @date 2020-12-27 00:50
 * 类说明: 商品的实体类
 */
public class GoodsInfo {
    private final String name;

    // 总销售额
    private double totalMoney;
    // 库存数
    private int storeNumber;

    public GoodsInfo(String name, int totalMoney, int storeNumber) {
        this.name = name;
        this.totalMoney = totalMoney;
        this.storeNumber = storeNumber;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void changeNumber(int sellNumber) {
        this.totalMoney += sellNumber * 25;
        this.storeNumber -= sellNumber;
    }
}
