package com.beiwu.ch1.sync;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-23 23:39
 * 实例和类 是可以并行的
 */
public class InstanceAndClass {
    private static class SynClass extends Thread {
        @Override
        public void run() {
            System.out.println("TestClass is running......");
            synClass();
        }
    }

    private static class InstanceSyn implements Runnable {
        private InstanceAndClass instanceAndClass;

        public InstanceSyn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            instanceAndClass.instance();
        }
    }

    public static synchronized void synClass() {
        System.out.println("synClass begin---->>>");
        SleepTools.second(1);
        System.out.println("synClass inner---->>>");
        SleepTools.second(1);
        System.out.println("synClass leave---->>>");
    }

    public synchronized void instance() {
        System.out.println("instance begin---->>>");
        SleepTools.second(1);
        System.out.println("instance inner---->>>");
        SleepTools.second(1);
        System.out.println("instance leave---->>>");
    }

    public static void main(String[] args) {
        SynClass synClass = new SynClass();
        Thread thread = new Thread(new InstanceSyn(new InstanceAndClass()));

        synClass.start();
        thread.start();
        SleepTools.second(2);
    }

}
