package com.beiwu.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2021-06-22 21:47
 */
public class CusTerminatedThreadPoolExecutor extends ThreadPoolExecutor {
    private long startTime = System.currentTimeMillis();

    public CusTerminatedThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                           long keepAliveTime,
                                           TimeUnit unit,
                                           BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CusTerminatedThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                           long keepAliveTime,
                                           TimeUnit unit,
                                           BlockingQueue<Runnable> workQueue,
                                           ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public CusTerminatedThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                           long keepAliveTime,
                                           TimeUnit unit,
                                           BlockingQueue<Runnable> workQueue,
                                           RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public CusTerminatedThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
                                           long keepAliveTime,
                                           TimeUnit unit,
                                           BlockingQueue<Runnable> workQueue,
                                           ThreadFactory threadFactory,
                                           RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory,
            handler);
    }

    @Override
    protected void terminated() {
        System.out.println("terminated");
        System.out.println(String
            .format("cost time: [%f] s", (System.currentTimeMillis() - startTime) * 1.0 / 1000));
        super.terminated();
    }
}
