package com.beiwu.ch4.condition;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-27 15:30
 */
public class TestCondition {
    public static void main(String[] args) {
        Express express = new Express("shanghai", 10);

        new Thread(() -> {
            express.watchKm();
        }).start();

        new Thread(() -> {
            express.watchSite();
        }).start();

        System.out.println("watch job init success");
        SleepTools.ms(500);



        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int newKm = express.getKm() + 10;
                express.changeKm(newKm);
            }).start();

            int finalI = i;
            new Thread(() -> {
                String newSite = express.getSite() + "_" + finalI;
                express.changeSite(newSite);
            }).start();
        }

        new Thread(() -> {
            express.changeSite("beijing");
        }).start();
        System.out.println("chang job init success");

    }
}
