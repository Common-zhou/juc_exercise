package com.beiwu.ch1.practice;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-24 17:24
 *
 * 《wait/notify实现生产者和消费者程序》
 * 采用多线程技术，例如wait/notify，设计实现一个符合生产者和消费者问题的程序，
 * 对某一个对象（枪膛）进行操作，其最大容量是20颗子弹，生产者线程是一个压入线程，
 * 它不断向枪膛中压入子弹，消费者线程是一个射出线程，它不断从枪膛中射出子弹。
 * 请实现上面的程序。
 *
 */
public class Exercise1 {

    public static void main(String[] args) {
        Gun gun = new Gun();
        new Thread(()->{
            gun.addBullet();
        }).start();
        new Thread(()->{
            gun.popBullet();
        }).start();

        SleepTools.second(5);
        System.out.println("主线程发出结束射击命令---");
        gun.stopShoot();
    }
}
