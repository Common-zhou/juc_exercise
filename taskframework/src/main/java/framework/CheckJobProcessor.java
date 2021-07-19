package framework;

import java.util.Map;
import java.util.concurrent.DelayQueue;

/**
 * 该类作用：维持一个延时队列，当延时队列中获取到元素时，说明该job需要从内存中清除，直接删除Map中key即可
 * @Author zhoubing
 * @Date 2021-07-05 10:58
 */
public class CheckJobProcessor {
    private DelayQueue<ItemVo<String>> queue = new DelayQueue<>();

    // 首先单例
    private CheckJobProcessor() {
    }

    private static class Holder {
        static final CheckJobProcessor processor = new CheckJobProcessor();
    }

    public static CheckJobProcessor getInstance() {
        return Holder.processor;
    }

    private static class FetchJob implements Runnable {
        private DelayQueue<ItemVo<String>> queue = CheckJobProcessor.getInstance().queue;

        private Map<String, JobInfo<?>> jobInfoMap = PendingJobPool.getMap();

        @Override
        public void run() {
            while (true) {
                try {
                    ItemVo<String> item = queue.take();
                    String jobName = item.getData();
                    jobInfoMap.remove(jobName);
                    System.out.println(String.format("%s 过期了，从缓存中清除.", jobName));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 任务完成后 将其放入延时队列
    public void putJob(String jobName, long expireTime) {
        ItemVo<String> itemVo = new ItemVo<>(expireTime, jobName);

        queue.offer(itemVo);

        System.out.println(String.format("%s 已经执行完成，放入延时队列，过期时间：%s", jobName, expireTime));
    }

    static {
        Thread thread = new Thread(new FetchJob());

        thread.setDaemon(true);
        thread.start();

        System.out.println("开启过期检查的守护线程......");
    }

}
