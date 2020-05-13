package com.geetest.c_015_lock;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author zhoubing
 * @date 2020-05-07 14:45
 */
public class TestVarHandle {
    int x = 0;
    private static VarHandle varHandle;

    static {
        try {
            varHandle = MethodHandles.lookup().findVarHandle(TestVarHandle.class, "x", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("hello");

        TestVarHandle testVarHandle = new TestVarHandle();
        System.out.println(testVarHandle.x);

        varHandle.set(testVarHandle, 10);
        System.out.println(testVarHandle.x);

        varHandle.compareAndSet(testVarHandle, 9, 10);
        System.out.println(testVarHandle.x);

        varHandle.compareAndSet(testVarHandle, 10, 16);
        System.out.println(testVarHandle.x);

        varHandle.getAndAdd(testVarHandle, 10);
        System.out.println(testVarHandle.x);

    }
}
