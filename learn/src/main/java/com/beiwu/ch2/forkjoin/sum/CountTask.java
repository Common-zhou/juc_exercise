package com.beiwu.ch2.forkjoin.sum;

import com.beiwu.ch1.tools.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author zhoubing
 * @date 2020-12-24 23:03
 *
 * invoke是调用执行方法
 * invokeAll也是调用执行方法
 * join是拿到结果
 */
public class CountTask {
    private static class SumTask extends RecursiveTask<Long> {
        private final static int THRESHOLD = MakeArray.ARRAY_LENGTH / 10;
        private int[] src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Long compute() {
            if (toIndex - fromIndex < THRESHOLD) {
                System.out.println(" from index = " + fromIndex + " toIndex = " + toIndex);

                long count = 0;
                for (int i = fromIndex; i <= toIndex; i++) {
                    // TODO 模拟耗时较长的过程
                    SleepTools.ms(1);
                    count = count + src[i];
                }
                return count;
            } else {
                // fromIndex -------- toIndex
                int mid = (fromIndex + toIndex) / 2;
                SumTask left = new SumTask(src, fromIndex, mid);
                SumTask right = new SumTask(src, mid + 1, toIndex);
                invokeAll(left, right);
                return left.join() + right.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] src = MakeArray.makeArray();

        SumTask innerFind = new SumTask(src, 0, src.length - 1);

        long start = System.currentTimeMillis();

        pool.invoke(innerFind);

        System.out.println("Task is Running......");

        System.out.println("The count is " + innerFind.join()
                + " spend time:" + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();

        Long sum = SumNormal.sum(src);
        System.out.println("single thread ---->   The count is " + sum
                + " spend time:" + (System.currentTimeMillis() - start) + " ms");



    }
}
