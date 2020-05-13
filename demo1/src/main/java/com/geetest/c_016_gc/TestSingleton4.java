package com.geetest.c_016_gc;

/**
 * @author zhoubing
 * @date 2020-05-07 17:33
 */
public class TestSingleton4 {
    private TestSingleton4() {
    }

    static TestSingleton4 getTestSingleton() {
        return innerClass.testSingleton4;
    }

    private static class innerClass {
        final static TestSingleton4 testSingleton4 = new TestSingleton4();
    }
}
