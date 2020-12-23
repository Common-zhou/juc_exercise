package com.beiwu.ch1.threadlocal;

/**
 * @author zhoubing
 * @date 2020-12-24 00:09
 */
public class ThreadLocalTest {
    private class UseThreadLocal extends Thread {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        public UseThreadLocal(){
            super();
            threadLocal.set(new Integer(0));
        }

        @Override
        public void run() {
//            threadLocal.set(new Integer(0));
            for (int i = 0; i < 10000; i++) {
                Integer integer = threadLocal.get();
                integer++;
                threadLocal.set(integer);
            }
            System.out.println(Thread.currentThread().getName() + "-->"
                    + threadLocal.get());
        }
    }
    public static void main(String[] args) {
        ThreadLocalTest test = new ThreadLocalTest();
        ThreadLocalTest.UseThreadLocal thread1 = test.new UseThreadLocal();
        ThreadLocalTest.UseThreadLocal thread2 = test.new UseThreadLocal();

        thread1.threadLocal.set(0);
        thread2.threadLocal.set(0);

        thread1.start();
        thread2.start();
    }
}
