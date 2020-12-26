package com.beiwu.ch2.future;

import com.beiwu.ch1.tools.SleepTools;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zhoubing
 * @date 2020-12-26 15:51
 * Callable使用 Callable可以拿到运行结果
 * 但是new Thread() 不能直接传入Callable
 * 学习FutureTask
 */
public class UseCallable {
    private static class UseCallableBean implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("Callable线程计算任务开始......");
            int sum = 0;
            for (int i = 0; i < 50000; i++) {
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Callable 接收到中断信号,中断计算");
                    return null;
                }
                sum += i;
                System.out.println("sum=" + sum);
            }
            System.out.println("Callable线程计算任务结束,结果是:" + sum + "......");

            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new UseCallableBean());

        new Thread(futureTask).start();

        SleepTools.ms(200);
        Random r = new Random();
        if(r.nextInt(100) > 50){
            futureTask.cancel(true);
            System.out.println("cancel work 中断计算");
        }else{
            Integer result = futureTask.get();
            System.out.println("Main thread 得到结果: " + result);

        }


    }
}
