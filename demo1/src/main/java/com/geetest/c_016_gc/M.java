package com.geetest.c_016_gc;

/**
 * @author zhoubing
 * @date 2020-05-07 15:55
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
