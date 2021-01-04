package com.beiwu.ch5.bq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2021-01-04 23:38
 */
public class ItemVo<T> implements Delayed {
    // 到期时间 但传入的数值代表过期的时长 传入单位毫秒
    private long activeTime;
    private T data;

    public ItemVo(long activeTime, T data) {
        this.activeTime = activeTime * 1000 + System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long d = unit.convert(this.activeTime - System.currentTimeMillis(), unit);
        return d;
    }

    @Override
    public int compareTo(Delayed o) {
        long d = (getDelay(TimeUnit.MILLISECONDS)
                - o.getDelay(TimeUnit.MILLISECONDS));
        if (d == 0) {
            return 0;
        } else {
            if (d < 0) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
