package com.beiwu.ch4.rwl;

import com.beiwu.ch1.tools.SleepTools;

import java.util.Random;

/**
 * @author zhoubing
 * @date 2020-12-27 01:00
 */
public class BusiApp {

    static final int readWriteRatio = 10;//读写线程的比例
    static final int minthreadCount = 3;//最少线程数

    // 读操作
    private static class GetThread implements Runnable {

        private GoodService goodService;

        public GetThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();

            for (int i = 0; i < 100; i++) {
                // 操作100次
                goodService.getNumber();
            }
            System.out.println(Thread.currentThread().getName() + " 读取商品耗时:" +
                    (System.currentTimeMillis() - start) + "ms----------");
        }
    }

    private static class SetThread extends Thread{
        private GoodService goodService;

        public SetThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long start = System.currentTimeMillis();
            Random r = new Random();
            for (int i = 0; i < 10; i++) {
                SleepTools.ms(50);
                goodService.setNumber(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()
            +"写商品耗时:" + (System.currentTimeMillis() - start) + "ms----------");
        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo = new GoodsInfo("Cup", 100000, 10000);
        //GoodService goodService = new UseRwLock(goodsInfo);
        GoodService goodService = new UseSyn(goodsInfo);

        for (int i = 0; i < minthreadCount; i++) {
            Thread setThread = new SetThread(goodService);
            for (int j = 0; j < readWriteRatio; j++) {
                Thread getThread = new Thread(new GetThread(goodService));
                getThread.start();
            }
            SleepTools.ms(100);
            setThread.start();

        }

    }
}
