package com.beiwu.ch1.tools;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-12-23 23:27
 */
public class SleepTools {
    /**
     * 按秒休眠
     *
     * @param seconds
     */
    public static final void second(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 按毫秒休眠
     *
     * @param ms
     */
    public static final void ms(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
