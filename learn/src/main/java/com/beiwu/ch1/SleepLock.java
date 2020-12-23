package com.beiwu.ch1;

/**
 * 测试sleep对锁的影响
 *  sleep时候 不会释放锁 会一直等到执行结束才会释放锁
 *  1.sleep()方法
 * 在指定时间内让当前正在执行的线程暂停执行，但不会释放“锁标志”。不推荐使用。
 * sleep()使当前线程进入阻塞状态，在指定时间内不会执行。
 *  sleep()会让线程交出CPU的执行权，但是不会释放锁。
 */
public class SleepLock {
    private Object lock = new Object();

    public static void main(String[] args) {
        SleepLock sleepTest = new SleepLock();
        Thread threadA = sleepTest.new ThreadSleep();
        threadA.setName("ThreadSleep");
        Thread threadB = sleepTest.new ThreadNotSleep();
        threadB.setName("ThreadNotSleep");
        threadA.start();
        try {
            Thread.sleep(1000);
            System.out.println(" Main slept!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadB.start();
    }

    private class ThreadSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock");
            try {

                synchronized(lock) {
                    System.out.println(threadName+" taking the lock");
                    Thread.sleep(5000);
                    System.out.println("Finish the work: "+threadName);
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }

    private class ThreadNotSleep extends Thread{

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName+" will take the lock time="+System.currentTimeMillis());
            synchronized(lock) {
                System.out.println(threadName+" taking the lock time="+System.currentTimeMillis());
                System.out.println("Finish the work: "+threadName);
            }
        }
    }
}
