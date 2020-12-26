package com.beiwu.ch4.rwl;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-27 00:59
 */
public class UseSyn implements GoodService {
    private GoodsInfo goodsInfo;

    public UseSyn(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized void setNumber(int number) {
        SleepTools.ms(5);
        goodsInfo.changeNumber(number);
    }

    @Override
    public synchronized GoodsInfo getNumber() {

        SleepTools.ms(5);
        return goodsInfo;
    }
}
