package com.beiwu.ch1.sync;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-23 23:20
 * 不同的锁 可以并行执行
 */
public class DiffInstance {

    public synchronized void instance() {
        SleepTools.second(3);
        System.out.println("synInstance is going..." + this.toString());

        SleepTools.second(3);
        System.out.println("synInstance ended..." + this.toString());
    }

    public synchronized void instance2() {
        SleepTools.second(3);
        System.out.println("synInstance2 is going..." + this.toString());

        SleepTools.second(3);
        System.out.println("synInstance2 ended..." + this.toString());
    }

    private static class CustomThread extends Thread {
        DiffInstance diffInstance;

        public CustomThread(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            diffInstance.instance();
        }
    }

    private static class CustomRunnable implements Runnable {
        private DiffInstance diffInstance;

        public CustomRunnable(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            diffInstance.instance2();
        }
    }

    public static void main(String[] args) {
        /**
         * 不同的对象  锁对象不同
         */
//        DiffInstance instance1 = new DiffInstance();
//        CustomThread t1 = new CustomThread(instance1);
//        DiffInstance instance2 = new DiffInstance();
//        CustomThread t2 = new CustomThread(instance2);
//
//        t1.start();
//        t2.start();
//        SleepTools.second(3);

        DiffInstance instance3 = new DiffInstance();
        DiffInstance instance4 = new DiffInstance();

        Thread t3 = new Thread(new CustomRunnable(instance3));
        Thread t4 = new Thread(new CustomRunnable(instance4));

        t3.start();
        t4.start();
        SleepTools.second(3);


    }
}
