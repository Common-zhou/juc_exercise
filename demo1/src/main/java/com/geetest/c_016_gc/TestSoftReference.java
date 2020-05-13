package com.geetest.c_016_gc;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-07 16:54
 */
public class TestSoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(softReference.get());
        System.gc();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(softReference.get());

        byte[] b = new byte[1024*1024*15];
        System.out.println(softReference.get());

    }
}
