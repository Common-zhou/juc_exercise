package com.geetest.c_015_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-04-22 15:39
 */
public class ReentrantLockTest3 {

    Lock lock = new ReentrantLock();

    void method() {
        if(lock.tryLock()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " : " + true);
        }else{
            System.out.println(Thread.currentThread().getName() + " : " + false);
        }

    }


    public static void main(String[] args) {
        ReentrantLockTest3 reentrantLockTest3 = new ReentrantLockTest3();
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            threadList.add(new Thread(reentrantLockTest3::method, "THREAD-" + i));
        }

        threadList.forEach(Thread::start);

        threadList.forEach(o->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


    }
}
