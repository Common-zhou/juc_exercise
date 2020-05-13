package com.geetest.c_016_gc;

import java.io.IOException;

/**
 * @author zhoubing
 * @date 2020-05-07 15:55
 */
public class TestGc {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;

        System.gc();

        System.in.read();
    }
}
