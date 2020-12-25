package com.beiwu.ch2.tools;

import com.beiwu.ch1.tools.SleepTools;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zhoubing
 * @date 2020-12-25 10:34
 * 使用CyclicBarrier
 * 类说明：演示CyclicBarrier用法,共5个子线程，他们全部完成工作后，交出自己结果，
 * 再被统一释放去做自己的事情，而交出的结果被另外的线程拿来拼接字符串
 *
 */
public class UseCyclicBarrier {
    // 可以传一个Runnable 这样await结束的时候 会调一下这个方法
    private static CyclicBarrier barrier = new CyclicBarrier(4);
    //    private static CyclicBarrier barrier = new CyclicBarrier(4, new CyclicResultRunnable());
    private static ConcurrentHashMap<String, Long> resultMap = new ConcurrentHashMap<>();

    private static class CyclicThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " enter Cyclic1");

            long id = Thread.currentThread().getId();
            resultMap.put(Thread.currentThread().getId() + "", id);
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " done Cyclic1");

            SleepTools.second(1);

            System.out.println(Thread.currentThread().getName() + " begin Cyclic2");

            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " end Cyclic2");

        }
    }

    private static class CyclicResultRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("begin resolve result");

            StringBuilder resBuilder = new StringBuilder();
            for (Map.Entry<String, Long> result : resultMap.entrySet()) {
                resBuilder.append("[" + result.getValue() + "]");
            }

            System.out.println("result:" + resBuilder.toString());
            System.out.println("do other business work......");
        }
    }


    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CyclicThread().start();
        }

        System.out.println("Main thread enter Cyclic1");
        SleepTools.second(1);

        barrier.await();
        System.out.println("Main thread done Cyclic1");

        System.out.println("first work done ------->>>>>>");

        SleepTools.second(1);

        System.out.println("Main thread enter Cyclic2");

        barrier.await();
        System.out.println("Main thread done Cyclic2");

        System.out.println("second work done ------->>>>>>");
    }
}
