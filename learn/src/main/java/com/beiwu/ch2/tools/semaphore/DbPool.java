package com.beiwu.ch2.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author zhoubing
 * @date 2020-12-25 16:01
 * Semaphore使用 使用Semaphore做流控 实现简易连接池
 */
public class DbPool {
    private static LinkedList<Connection> pool = new LinkedList<>();
    private static Semaphore useful, useless;

    public DbPool(int initThreadSize) {
        if (initThreadSize > 0) {
            for (int i = 0; i < initThreadSize; i++) {
                pool.addLast(MyConnection.fetchConnection());
            }
            useful = new Semaphore(initThreadSize);
            useless = new Semaphore(0);
        }
    }

    public Connection fetchConnection() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
//            System.out.println(Thread.currentThread().getName() + " 获取到了,当前还剩"+ pool.size() + "个连接......");
        }
        useless.release();
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        if (connection != null){
            System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接!!"
                    +"可用连接数："+useful.availablePermits());
            useless.acquire();

//            System.out.println(Thread.currentThread().getName() + " 归还了连接,当前还剩"+ pool.size() + "个连接......");

            synchronized (pool){
                pool.addLast(connection);
            }

            useful.release();
        }
    }
}
