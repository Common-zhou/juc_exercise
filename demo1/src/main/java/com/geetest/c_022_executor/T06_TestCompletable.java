package com.geetest.c_022_executor;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-05-15 11:49
 */
public class T06_TestCompletable {
    static Random random = new Random();

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
//        long start = System.currentTimeMillis();
//        double priceOfTM = priceOfTM();
//        double priceOfJD = priceOfJD();
//        double priceOfPDD = priceOfPDD();
//
//        System.out.println("consume time:" + (System.currentTimeMillis() - start));
//
//        System.out.printf("TM:%f   JD:%f   PDD:%f", priceOfTM, priceOfJD, priceOfPDD);


        long start = System.currentTimeMillis();
        CompletableFuture<Double> completableFutureTM = CompletableFuture.supplyAsync(() -> priceOfTM());
        CompletableFuture<Double> completableFutureJD = CompletableFuture.supplyAsync(() -> priceOfJD());
        CompletableFuture<Double> completableFuturePDD = CompletableFuture.supplyAsync(() -> priceOfPDD());

        CompletableFuture.allOf(completableFutureTM, completableFutureJD, completableFuturePDD).join();

        System.out.println(completableFutureTM.get().toString());
        System.out.println(completableFutureJD.get().toString());
        System.out.println(completableFuturePDD.get().toString());

        System.out.println("consume time:" + (System.currentTimeMillis() - start));


    }

    public static double priceOfTM() {
        delay();
        return 1.0;
    }

    public static double priceOfJD() {
        delay();
        return 2.0;
    }

    public static double priceOfPDD() {
        delay();
        return 3.0;
    }

    public static void delay() {
        int bound = random.nextInt(500);
        try {
            TimeUnit.MICROSECONDS.sleep(bound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Sleep time:" + bound);
    }
}
