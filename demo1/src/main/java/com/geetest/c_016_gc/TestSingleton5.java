package com.geetest.c_016_gc;

/**
 * @author zhoubing
 * @date 2020-05-07 19:07
 */
public enum TestSingleton5 {
    INATANCE;

    public static void main(String[] args) {
        TestSingleton5 instance1 = TestSingleton5.INATANCE;
        TestSingleton5 instance2 = TestSingleton5.INATANCE;
        System.out.println(instance1 == instance2);
    }
}
