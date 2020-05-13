package com.geetest.c_016_gc;

/**
 * @author zhoubing
 * @date 2020-05-07 17:31
 */
public class TestSingleton {
    private TestSingleton() {
    }

    private static TestSingleton instance = new TestSingleton();

    public static TestSingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        TestSingleton singleton1 = getInstance();
        TestSingleton singleton2 = getInstance();

        System.out.println(singleton1 == singleton2);
    }
}
