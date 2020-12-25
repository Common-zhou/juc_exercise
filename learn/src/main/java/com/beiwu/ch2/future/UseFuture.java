package com.beiwu.ch2.future;

import com.beiwu.ch1.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhoubing
 * @date 2020-12-25 16:47
 *
 * Callable 可以拿到线程执行的结果
 * Runnable 不能拿到执行结果
 *
 * 但是new Thread()的时候，只能传入Runnable接口的实例
 *
 * RunnableFuture(Interface) extends Runnable, Future<V>
 *
 * FutureTask implements RunnableFuture<V> 它实现了这个接口
 * 并且它还有一个构造函数 是接受Callable接口的
 *
 */
public class UseFuture {
    private static class UseCallable implements Callable<Integer> {
        private int sum;

        @Override
        public Integer call() throws Exception {
            System.out.println("callable线程开始计算......");

            for (int i = 0; i < 5000; i++) {
                if (Thread.currentThread().isInterrupted()){
                    // 必须要自己实现这个逻辑 futureTask的cancel其实就是调用了interrupt方法
                    System.out.println("Callable子线程计算任务终止");
                    return null;
                }
                sum += i;
                System.out.println("sum=" + sum);
            }

            System.out.println("callable线程计算结束,结果为:" + sum + "......");
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UseCallable useCallable = new UseCallable();

        FutureTask<Integer> futureTask = new FutureTask<>(useCallable);
        Random r = new Random();
        new Thread(futureTask).start();

//        Integer result = futureTask.get();
//        System.out.println("Main thread result:" + result);

        SleepTools.ms(1);
        if(r.nextInt(100) > 50){
            System.out.println("Main thread result:" + futureTask.get());
        }else{
            System.out.println("Cancel............");
            futureTask.cancel(true);
        }

    }
}
