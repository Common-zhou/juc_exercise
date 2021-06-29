package com.beiwu;

import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 因此我们通过一个线程运行Callable，但是Thread不支持构造方法中传递Callable的实例，
 * 所以我们需要通过FutureTask把一个Callable包装成Runnable，然后再通过这个FutureTask拿到Callable运行后的返回值。
 * @author zhoubing
 * @date 2021-06-25 17:30
 */
public class LearnRunnable {

    public static void main(String[] args) {

        Callable callable = new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                TimeUnit.SECONDS.sleep(5);

                HashMap<String, String> map = new HashMap<>();
                map.put("k1", "hello world1");
                map.put("k2", "hello world2");
                return map;
            }
        };

        FutureTask<Object> task = new FutureTask<Object>(callable);
        Thread t = new Thread(task);
        t.start();

        try {
            Object res = task.get();
            System.out.println(res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
