package demo;

import framework.ITaskProcessor;
import framework.PendingJobPool;
import framework.utils.SleepTools;
import framework.TaskResult;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author zhoubing
 * @Date 2021-07-05 10:13
 */
public class PaperTask {

    private static final AtomicInteger count = new AtomicInteger(0);
    private static final String JOB_NAME = "paper";


    private static class QueryResult implements Runnable {
        private PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 350) {
                List<TaskResult<String>> taskDetail = pool.getTaskDetail(JOB_NAME);
                if (!taskDetail.isEmpty()) {
                    System.out.println(pool.getProgress(JOB_NAME));
                    System.out.println(taskDetail.size());
                }
                SleepTools.seconds(1);
                i++;
            }
        }
    }

    public static void main(String[] args) {
        PendingJobPool pendingJobPool = PendingJobPool.getInstance();

        ITaskProcessor<Integer, String> processor = new PaperTaskProcessor();


        pendingJobPool.registerJob(JOB_NAME, processor, 5000, 15);

        ExecutorService service = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 50; i++) {
            // 50个线程
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        int id = count.incrementAndGet();
                        pendingJobPool.putTask(JOB_NAME, id);
                    }
                }
            });
        }

        Thread t = new Thread(new QueryResult(pendingJobPool));
        t.start();


    }

}
