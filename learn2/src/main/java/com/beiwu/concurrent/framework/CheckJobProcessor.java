package com.beiwu.concurrent.framework;

import com.beiwu.concurrent.framework.vo.ItemVo;
import com.beiwu.concurrent.framework.vo.JobInfo;
import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * @author zhoubing
 * @date 2021-07-01 22:30
 */
public class CheckJobProcessor {
    /*存放任务的队列*/

    private static DelayQueue<ItemVo<String>> queue = new DelayQueue<>();

    private static class FetchJob implements Runnable {

        private static Map<String, JobInfo<?>> jobInfoMap = PendingJobPool.getMap();

        @Override
        public void run() {
            while (true) {
                try {
                    ItemVo<String> take = queue.take();

                    String jobName = take.getData();
                    jobInfoMap.remove(jobName);

                    System.out.println(String.format("%s 过期了，从缓存中清除", jobName));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static {
        Thread thread = new Thread(new FetchJob());
        thread.setDaemon(true);
        thread.start();
        System.out.println("已经开启过期检查守护线程......");
    }

}
