package com.beiwu.delayed;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhoubing
 * @Date 2021-07-05 16:41
 */
public class CusItemVo<T> implements Delayed {

    private T data;
    private long expireDate;

    public CusItemVo(T data, long expireDate) {
        this.data = data;
        this.expireDate = (System.currentTimeMillis() + expireDate * 1000);
    }

    public T getData() {
        return data;
    }

    public long getExpireDate() {
        return expireDate;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remain = unit.convert(expireDate - System.currentTimeMillis(), unit);
        return remain;
    }

    @Override
    public int compareTo(Delayed o) {

        // 用当前的减传入的
        long delay = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);

        if (delay == 0) {
            return 0;
        } else {
            if (delay < 0) {
                return -1;
            } else {
                return 1;
            }
        }

    }
}
