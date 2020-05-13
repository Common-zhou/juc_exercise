package com.geetest.c_011_volatile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-04-21 20:34
 */
public class VolatileTest {
    volatile int total = 0;

    void m(){
        for (int i = 0; i < 10000; i++) {
            total++;
        }
    }

    public static void main(String[] args) {
        VolatileTest volatileTest = new VolatileTest();

        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(volatileTest::m, "THREAD" + i);
            threadList.add(thread);
        }

        threadList.forEach(o->o.start());

        threadList.forEach(o-> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(volatileTest.total);

    }
}
