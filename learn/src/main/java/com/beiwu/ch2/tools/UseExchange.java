package com.beiwu.ch2.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * @author zhoubing
 * @date 2020-12-25 12:04
 * 练习使用Exchanger 两个线程之间交换数据
 */
public class UseExchange {
    private static final Exchanger<Set<String>> exchange = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            Set<String> setA = new HashSet<>();
            setA.add("zhangsan");
            setA.add("lisi");
            try {
                Set<String> exchanged = exchange.exchange(setA);
                System.out.println(Thread.currentThread().getName() + exchanged);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(()->{
            Set<String> setB = new HashSet<>();
            setB.add("hello");
            setB.add("setB");
            try {
                Set<String> exchanged = UseExchange.exchange.exchange(setB);
                System.out.println(Thread.currentThread().getName() + exchanged);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


    }
}
