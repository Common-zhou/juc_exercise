package com.beiwu.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2021-07-02 00:35
 */
public class SleepTools {
    public static void sleepMs(int ms){
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleepS(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
