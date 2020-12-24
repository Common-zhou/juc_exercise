package com.beiwu.ch1.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhoubing
 * @date 2020-12-24 17:01
 */
public class DbPoolTest {
    private static DbPool dbPool = new DbPool(10);
    private static DbPoolTest dbPoolTest = new DbPoolTest();
    private static AtomicInteger successNum = new AtomicInteger(0);
    private static AtomicInteger failNum = new AtomicInteger(0);

    public void executeSql() {
        Connection connection = dbPool.fetchConnection(1000);
        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                connection.commit();
                successNum.incrementAndGet();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                dbPool.releaseConnection(connection);
            }
        } else {
            failNum.incrementAndGet();
            System.out.println(Thread.currentThread().getName()
                    + "等待超时!");
        }
    }

    public static void startThread(int threadNum, int eachThreadGet) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < eachThreadGet; j++) {
                    dbPoolTest.executeSql();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println("获取成功次数 :" + successNum.get());
        System.out.println("获取失败次数 :" + failNum.get());

    }

    public static void main(String[] args) throws InterruptedException {
        startThread(20, 20);
    }
}
