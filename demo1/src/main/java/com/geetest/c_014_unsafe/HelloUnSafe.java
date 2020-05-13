package com.geetest.c_014_unsafe;

import sun.misc.Unsafe;

/**
 * @author zhoubing
 * @date 2020-04-21 22:36
 */
public class HelloUnSafe {

    void method(){
        System.out.println("hello");
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = null;
        try {
            unsafe = Unsafe.getUnsafe();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HelloUnSafe helloUnSafe = (HelloUnSafe) unsafe.allocateInstance(HelloUnSafe.class);

        helloUnSafe.method();
    }
}
