package com.geetest.c_011_volatile;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-21 20:21
 */
public class HelloVolatile {

    /*volatile*/ boolean flag = true;

    void method(){
        long startTime = System.currentTimeMillis();
        System.out.println("method is start");
        while (flag){

        }
        long endTime = System.currentTimeMillis();
        System.out.println("method is end" + (endTime - startTime));
    }

    public static void main(String[] args) throws InterruptedException {
        HelloVolatile helloVolatile = new HelloVolatile();

        Thread thread = new Thread(helloVolatile::method);

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        helloVolatile.flag = false;

    }
}
