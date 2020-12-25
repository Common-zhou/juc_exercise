package com.beiwu.ch2.tools.semaphore;

import com.beiwu.ch1.tools.SleepTools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2020-12-25 16:11
 */
public class SemaphoreTest {

    private static DbPool pool = new DbPool(5);

    private static class SemaphorePoolThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 1; i++) {
                    Connection connection = pool.fetchConnection();
//                    System.out.println(Thread.currentThread().getName() + ": 获取到了连接------");
                    Statement statement = connection.createStatement();
                    connection.commit();
                    Random r = new Random();
                    SleepTools.ms(100 + r.nextInt(10));
                    pool.releaseConnection(connection);
//                    System.out.println(Thread.currentThread().getName() + ": 归还了连接------");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new SemaphorePoolThread().start();
        }
        System.out.println("Main thread init success!");
    }
}
