package com.beiwu.ch1.practice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-12-24 17:24
 * <p>
 * 模拟枪 子弹最大数目20
 */
public class Gun {
    private final int NUMBER_BULLET_MAX = 20;
    private final List<Integer> bullets = new ArrayList<>();
    private volatile boolean flag = true;
    private int addBulletSpeed = 3;
    private int popBulletSpeed = 2;

    public void addBullet() {
        synchronized (bullets) {
            while (flag) {
                if (bullets.size() + addBulletSpeed > NUMBER_BULLET_MAX) {
                    // 如果满了  则放弃添加
                    System.out.println("枪膛满了, 目前子弹数目:" + bullets.size());
                    try {
                        bullets.notify();
                        bullets.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    for (int i = 0; i < addBulletSpeed; i++) {
                        bullets.add(i);
                    }
                    System.out.println("上弹:" + addBulletSpeed + " 颗");
                }
            }
            System.out.println("压弹程序终止---");
            bullets.notifyAll();
        }

    }

    public void popBullet() {
        synchronized (bullets) {
            while (flag) {
                if (bullets.size() - popBulletSpeed < 0) {
                    // 如果空了 则放弃射出
                    System.out.println("枪膛空了, 目前子弹数目:" + bullets.size());
                    try {
                        bullets.notify();
                        bullets.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    Iterator<Integer> iterator = bullets.iterator();
                    for (int i = 0; i < popBulletSpeed && iterator.hasNext(); i++) {
                        iterator.next();
                        iterator.remove();
                    }
                    System.out.println("射出子弹:" + popBulletSpeed + " 颗");
                }
            }
            System.out.println("发射程序终止---");
            bullets.notifyAll();
        }
    }

    public void stopShoot(){
        this.flag = false;
    }
}
