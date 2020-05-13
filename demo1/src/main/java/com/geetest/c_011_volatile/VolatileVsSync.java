package com.geetest.c_011_volatile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-04-21 21:24
 */
public class VolatileVsSync {
    int count = 0;

    synchronized void m(){
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();
        VolatileVsSync volatileVsSync = new VolatileVsSync();

        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(volatileVsSync::m));
        }

        threadList.forEach(Thread::start);

        threadList.forEach(o->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(volatileVsSync.count);
    }
}
