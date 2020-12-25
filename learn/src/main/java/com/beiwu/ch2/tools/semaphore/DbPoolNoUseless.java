package com.beiwu.ch2.tools.semaphore;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author zhoubing
 * @date 2020-12-25 16:31
 */
public class DbPoolNoUseless {
    private static LinkedList<Connection> pool = new LinkedList<>();
    private static Semaphore useful;

    public DbPoolNoUseless(int initThreadSize) {
        if (initThreadSize > 0) {
            for (int i = 0; i < initThreadSize; i++) {
                pool.addLast(MyConnection.fetchConnection());
            }
            useful = new Semaphore(initThreadSize);
        }
    }

    public Connection fetchConnection() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) throws InterruptedException {
        if (connection != null){
            System.out.println("当前有"+useful.getQueueLength()+"个线程等待数据库连接!!"
                    +"可用连接数："+useful.availablePermits());

            synchronized (pool){
                pool.addLast(connection);
            }

            useful.release();
        }
    }
}
