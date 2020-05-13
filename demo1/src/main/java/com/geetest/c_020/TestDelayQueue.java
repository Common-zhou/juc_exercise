package com.geetest.c_020;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-13 10:38
 */
public class TestDelayQueue {

    static class MyTask implements Delayed {
        String name;
        long runningTime;

        public MyTask(String name, long runningTime) {
            this.name = name;
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long consumeTime = runningTime - System.currentTimeMillis();
            return unit.convert(consumeTime, TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MICROSECONDS) < o.getDelay(TimeUnit.MICROSECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MICROSECONDS) > o.getDelay(TimeUnit.MICROSECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "MyTask{" +
                    "name='" + name + '\'' +
                    ", runningTime=" + runningTime +
                    '}';
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<MyTask> blockingQueue = new DelayQueue<>();
        blockingQueue.add(new MyTask("task-1", 10));
        blockingQueue.add(new MyTask("task-2", 11));
        blockingQueue.add(new MyTask("task-3", 9));
        blockingQueue.add(new MyTask("task-4", 8));
        blockingQueue.add(new MyTask("task-5", 2));
        blockingQueue.add(new MyTask("task-6", 6));
        blockingQueue.add(new MyTask("task-7", 1));

        for (int i = 0; i < 7; i++) {
            System.out.println(blockingQueue.take());
        }
    }
}
