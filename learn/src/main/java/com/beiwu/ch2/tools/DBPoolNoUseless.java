package com.beiwu.ch2.tools;

import com.beiwu.ch1.tools.SleepTools;
import com.beiwu.ch2.tools.semaphore.MyConnection;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 老师的案例 用来说明 如果不将空位置试做一个资源 有可能会导致用户自己创建线程往里丢
 */
public class DBPoolNoUseless {

    private final static int POOL_SIZE = 10;
    private final Semaphore useful;
    //存放数据库连接的容器
    private static LinkedList<Connection> pool = new LinkedList<Connection>();

    //初始化池
    static {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.addLast(MyConnection.fetchConnection());
        }
    }

    public DBPoolNoUseless() {
        this.useful = new Semaphore(10);
    }

    /*归还连接*/
    public void returnConnect(Connection connection) throws InterruptedException {
        if (connection != null) {
            System.out.println("当前有" + useful.getQueueLength() + "个线程等待数据库连接!!"
                    + "可用连接数：" + useful.availablePermits());
            synchronized (pool) {
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    /*从池子拿连接*/
    public Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool) {
            connection = pool.removeFirst();
        }
        return connection;
    }

    private static DBPoolNoUseless dbPoolNoUseless = new DBPoolNoUseless();

    private static class BusiThread extends Thread {
        @Override
        public void run() {
            Random r = new Random();//让每个线程持有连接的时间不一样
            long start = System.currentTimeMillis();
            try {
                Connection connection = dbPoolNoUseless.takeConnect();
                System.out.println("Thread_" + Thread.currentThread().getId()
                        + "_获取数据库连接共耗时【" + (System.currentTimeMillis() - start) + "】ms.");
                SleepTools.ms(100 + r.nextInt(100));//模拟业务操作，线程持有连接查询数据
                System.out.println("查询数据完成，归还连接！");
                dbPoolNoUseless.returnConnect(connection);
//                dbPoolNoUseless.returnConnect(new MyConnection());
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread thread = new BusiThread();
            thread.start();
        }
    }

}
