package com.beiwu.ch3;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author zhoubing
 * @date 2020-12-26 16:28
 * AtomicIntegerArray 对其进行操作  原数组不会发生变化
 */
public class UseAtomicArray {
    static int[] value = new int[]{1,2};
    static AtomicIntegerArray arrayAtomic = new AtomicIntegerArray(value);

    public static void main(String[] args) {
        arrayAtomic.set(0, 3);
        System.out.println(arrayAtomic.get(0));
        System.out.println(value[0]);
    }
}
