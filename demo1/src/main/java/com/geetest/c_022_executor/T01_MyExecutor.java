package com.geetest.c_022_executor;

import java.util.concurrent.Executor;

/**
 * @author zhoubing
 * @date 2020-05-15 09:57
 */
public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println(Thread.currentThread().getName() + " hello!"));
    }
}
