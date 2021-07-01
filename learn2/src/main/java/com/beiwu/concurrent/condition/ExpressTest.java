package com.beiwu.concurrent.condition;

/**
 * @author zhoubing
 * @date 2021-06-28 23:38
 */
public class ExpressTest {

    public static void main(String[] args) {


        ExpressCond cond = new ExpressCond();

        new Thread(() -> {
            cond.waitKm();
        }).start();

        new Thread(() -> {
            cond.waitSite();
        }).start();


        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                cond.changeKm();
            }).start();
        }


        new Thread(() -> {
            cond.changeSite("hubei");
        }).start();

        new Thread(() -> {
            cond.changeSite("ShangHai");
        }).start();


    }
}
