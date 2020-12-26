package com.beiwu.ch3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *类说明：有一个残缺A类实现了线程安全的：
 *get方法和compareAndSet()方法
 *自行实现它的递增方法
 */
public class HalfAtomicInt {
    private AtomicInteger atomicI = new AtomicInteger(0);

    /*请完成这个递增方法*/
    public void increament() {
        while (true){
            int oldValue = atomicI.get();
            if(compareAndSet(oldValue, oldValue+1)){
                break;
            }
        }
    }

    public int getCount() {
    	return atomicI.get();
    }

    public boolean compareAndSet(int oldValue,int newValue){
        return atomicI.compareAndSet(oldValue,newValue);
    }

    public static void main(String[] args) throws InterruptedException {
        HalfAtomicInt halfAtomicInt = new HalfAtomicInt();
        for(int i=0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<1000;i++){
                        halfAtomicInt.increament();
                    }
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(halfAtomicInt.getCount());
    }

}
