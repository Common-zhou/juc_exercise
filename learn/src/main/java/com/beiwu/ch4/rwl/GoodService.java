package com.beiwu.ch4.rwl;

/**
 * @author zhoubing
 * @date 2020-12-27 00:49
 */
public interface GoodService {
    // 设置商品的数目
    void setNumber(int number);

    // 获得商品的数目
    GoodsInfo getNumber();
}
