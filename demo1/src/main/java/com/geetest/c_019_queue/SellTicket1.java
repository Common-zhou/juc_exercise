package com.geetest.c_019_queue;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-13 00:25
 */
public class SellTicket1 {
    static Vector<String> vector = new Vector<>();

    static {
        for (int i = 0; i < 100; i++) {
            vector.add("票编号" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                while (true) {
                    if (vector.size() > 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("售出票:" + vector.remove(0));
                    }else{
                        break;
                    }
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("=================end======================");
    }

}
