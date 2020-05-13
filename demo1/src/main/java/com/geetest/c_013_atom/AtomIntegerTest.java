package com.geetest.c_013_atom;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2020-04-21 21:56
 */
public class AtomIntegerTest {

    AtomicInteger atomicInteger = new AtomicInteger(0);

    void method(){
        for (int i = 0; i < 1000000; i++) {
            atomicInteger.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomIntegerTest atomIntegerTest = new AtomIntegerTest();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(atomIntegerTest::method);
            thread.start();
            thread.join();
        }


        System.out.println(atomIntegerTest.atomicInteger.get());
    }
}
