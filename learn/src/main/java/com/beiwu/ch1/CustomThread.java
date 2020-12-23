package com.beiwu.ch1;

/**
 * @author zhoubing
 * @date 2020-12-23 11:28
 */
public class CustomThread extends Thread {
    @Override
    public void run() {
        System.out.println("CustomThread extends Thread...");
    }
}
