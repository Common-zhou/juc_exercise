package com.geetest.c_019_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-13 07:50
 */
public class SellTicket4 {

    private static Queue<String> queue = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 100; i++) {
            queue.add("票编号 " + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(()->{
                while (true){
                    String poll = queue.poll();
                    if (poll != null){
                        try {
                            TimeUnit.MICROSECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("卖出去票,编号:" + poll);
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
