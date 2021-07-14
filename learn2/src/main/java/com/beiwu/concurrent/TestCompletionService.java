package com.beiwu.concurrent;

import java.util.Random;
import java.util.concurrent.*;

/**
 * CompletionService 完成队列。线程池可以获得完成的。
 * @Author zhoubing
 * @Date 2021-07-14 11:12
 */
public class TestCompletionService {

    private static Random random = new Random();

    public static class JobClass implements Callable<String> {

        @Override
        public String call() throws Exception {
            int tmp = random.nextInt(5) + 2;

            SleepTools.sleepS(tmp);

            return "sleep seconds:" + tmp;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        CompletionService completionService = new ExecutorCompletionService(service);

        int count = 5;
        for (int i = 0; i < count; i++) {
            completionService.submit(new JobClass());
        }

        for (int i = 0; i < count; i++) {
            Future future = completionService.take();
            System.out.println(future.get());
        }

    }
}
