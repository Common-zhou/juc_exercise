package com.beiwu.learn_queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhoubing
 * @Date 2021-07-06 13:56
 */
public class ItemVO<T> implements Delayed {

    private long expireTimeSeconds;
    private T data;

    public ItemVO(long expireTimeSeconds, T data) {
        this.expireTimeSeconds = System.currentTimeMillis() + expireTimeSeconds * 1000;
        this.data = data;
    }

    public long getExpireTimeSeconds() {
        return expireTimeSeconds;
    }

    public T getData() {
        return data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long remaining = unit.convert(expireTimeSeconds - System.currentTimeMillis(), unit);
        return remaining;
    }

    @Override
    public int compareTo(Delayed o) {
        long delay = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        if (delay == 0) {
            return 0;
        } else {
            if (delay < 0) {
                return -1;
            }
            return 1;
        }

    }
}
