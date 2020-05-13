package com.geetest.c_015_lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhoubing
 * @date 2020-04-22 15:54
 */
public class ReentrantLockTest4 {
    Lock lock = new ReentrantLock();

    void method() {
        //不成功
        boolean b = false;
        try {
            b = lock.tryLock(2, TimeUnit.SECONDS);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (b){
                System.out.println(Thread.currentThread().getName() + " :" + " unlock");
                lock.unlock();
            }else{
                System.out.println(Thread.currentThread().getName() + " :" + " does't get lock.");
            }
        }
    }

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>();

        ReentrantLockTest4 reentrantLockTest4 = new ReentrantLockTest4();
        for (int i = 0; i < 100; i++) {
            threadList.add(new Thread(reentrantLockTest4::method, "THREAD-" + i));
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
