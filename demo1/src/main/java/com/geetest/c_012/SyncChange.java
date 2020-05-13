package com.geetest.c_012;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-04-21 21:30
 */
public class SyncChange {
    Object object = new Object();
    int count = 0;

    void method() {
        synchronized (object) {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        }
    }

    public static void main(String[] args) {
        SyncChange syncChange = new SyncChange();

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(syncChange::method));
        }


        for (int i = 0; i < 100; i++) {
            Thread thread = threadList.get(i);
            thread.start();
            syncChange.object = new Object();
        }

        threadList.forEach(o -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(syncChange.count);
    }

}
