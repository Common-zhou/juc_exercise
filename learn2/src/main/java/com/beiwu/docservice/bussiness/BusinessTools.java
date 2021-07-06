package com.beiwu.docservice.bussiness;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhoubing
 * @Date 2021-07-06 18:02
 */
public class BusinessTools {
    public static void businessMs(long ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void businessSeconds(long seconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
