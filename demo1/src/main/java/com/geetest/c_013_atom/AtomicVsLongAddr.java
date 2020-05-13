package com.geetest.c_013_atom;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author zhoubing
 * @date 2020-04-21 22:04
 */
public class AtomicVsLongAddr {
    static int count = 0;
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    static LongAdder longAdder = new LongAdder();

    void method1() {
        for (int i = 0; i < 100000; i++) {
            atomicInteger.incrementAndGet();
        }
    }

    void method2() {
        for (int i = 0; i < 100000; i++) {
            longAdder.increment();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicVsLongAddr atomicVsLongAddr = new AtomicVsLongAddr();

        Thread[] threads = new Thread[2000];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(atomicVsLongAddr::method1);

        }

        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(atomicInteger.get() + "   ;atomicInteger consume Time:" + (System.currentTimeMillis() - startTime));

        final Object lock = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(()->{
                synchronized (lock){
                    for (int j = 0; j < 100000; j++) {
                        count++;
                    }
            }
            });
        }
        startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(count + "   ;lock consume Time:" + (System.currentTimeMillis() - startTime));

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(atomicVsLongAddr::method2);
        }

        startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(longAdder.sum() + "   ;longAddr consume Time:" + (System.currentTimeMillis() - startTime));
    }

}
