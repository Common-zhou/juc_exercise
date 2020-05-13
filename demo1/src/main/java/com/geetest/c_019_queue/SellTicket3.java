package com.geetest.c_019_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-13 07:50
 */
public class SellTicket3 {

    private static List<String> tickets = new ArrayList<>();

    static {
        for (int i = 0; i < 100; i++) {
            tickets.add("票编号 " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(()->{
                while (true){
                    if (tickets.size() > 0){
                        try {
                            TimeUnit.MICROSECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        String remove = tickets.remove(0);
                        System.out.println("卖出去票,编号:" + remove);
                    }else{
                        break;
                    }
                }
            }));
        }

        threadList.forEach(Thread::start);
        for (Thread thread : threadList) {
            thread.join();
        }
    }
}
