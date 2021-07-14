package com.beiwu.concurrent.framework.demo;

import com.beiwu.concurrent.SleepTools;
import com.beiwu.concurrent.framework.PendingJobPool;
import com.beiwu.concurrent.framework.vo.TaskResult;
import java.util.List;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2021-07-02 00:38
 */
public class AppTest {
    private final static String JOB_NAME = "计算数值";
    private final static int JOB_LENGTH = 1000;

    private static class QueryResult implements Runnable {

        private PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            super();
            this.pool = pool;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 350) {
                List<TaskResult<String>> taskDetail = pool.getTaskDetail(JOB_NAME);
                if (!taskDetail.isEmpty()) {
                    System.out.println(pool.getTaskProgess(JOB_NAME));
                    System.out.println(taskDetail);
                }
                SleepTools.sleepMs(100);
                i++;
            }
        }
    }

    public static void main(String[] args) {
        MyTask myTask = new MyTask();
        PendingJobPool jobPool = PendingJobPool.getInstance();

        jobPool.registerJob(JOB_NAME, JOB_LENGTH, myTask, 5);
        Random random = new Random();
        for (int i = 0; i < JOB_LENGTH; i++) {
            jobPool.putTask(JOB_NAME, random.nextInt(1000));
        }

        Thread thread = new Thread(new QueryResult(jobPool));
        thread.start();

    }
}
