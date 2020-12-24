package com.beiwu.ch1.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author zhoubing
 * @date 2020-12-24 16:40
 * 简易线程池 获取连接
 */
public class DbPool {
    private static LinkedList<Connection> pool = new LinkedList<>();
    private static final int MAX_POOL_SIZE = 10;

//    static {
//        for (int i = 0; i < MAX_POOL_SIZE; i++) {
//            pool.addLast(MyConnection.fetchConnection());
//        }
//    }
    public DbPool(int initialSize){
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(MyConnection.fetchConnection());
            }
        }
    }

    public Connection fetchConnection(long ms){
        //TODO 在m毫秒内 如果拿不到连接 会返回null

        synchronized (pool){
            // 对连接池加锁
            long endTime = System.currentTimeMillis() + ms;
            long remainTime = ms;
            while (pool.isEmpty() && remainTime > 0){
                try {
                    pool.wait(remainTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                remainTime = endTime - System.currentTimeMillis();
            }

            Connection connection = null;
            if (!pool.isEmpty()){
                connection = pool.removeFirst();
            }
            return connection;
        }
    }

    public void releaseConnection(Connection connection){
        // TODO 将连接放回
        synchronized (pool){
            pool.addLast(connection);
            pool.notifyAll();
        }
    }
}
