package com.geetest.c_017_dp;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-09 08:32
 */
public class TestThreadLocal2 {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set("zhangsan");
        }).start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set("lisi");
            System.out.println(threadLocal.get());
        }).start();
    }
}
