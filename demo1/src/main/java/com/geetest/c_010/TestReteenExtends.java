package com.geetest.c_010;

import java.util.concurrent.TimeUnit;

/**
 * @author zhoubing
 * @date 2020-04-19 15:52
 */
public class TestReteenExtends {

    static class Father{
        public synchronized void method(){
            System.out.println("Father started....");

            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Father end....");
        }
    }

    static class Son extends Father{
        @Override
        public synchronized void method() {
            System.out.println("Son started....");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            super.method();

            System.out.println("Son end....");
        }
    }

    public static void main(String[] args) {
        Son son = new Son();
        new Thread(son::method).start();
    }

}
