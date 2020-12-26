package com.beiwu.ch3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2020-12-26 23:58
 *
 * compareAndSet 它的原子性是由cpu和内存来处理的
 * 传入的是一个新值 一个旧值
 * 如果旧值和当前值相同  则将新值赋值 返回true
 * 否则 返回false
 * 不过 它会造成ABA问题、资源浪费问题
 * ABA就是 之前是A  别的线程操作后变成B 后来又操作成A
 *      这时候 当前线程不知道这个变化过程的 它认为两个A相同  则直接赋值
 *      解决方法就是 设置一个版本号
 */
public class UseCompareAndSet {
    public static void main(String[] args) {
        AtomicInteger atomicNumber = new AtomicInteger();
        boolean casResult = atomicNumber.compareAndSet(0, 1);
        System.out.println("0--1, compareAndSwap result:" + casResult);

        casResult = atomicNumber.compareAndSet(2, 3);
        System.out.println("2--3, compareAndSwap result:" + casResult);

    }
}
