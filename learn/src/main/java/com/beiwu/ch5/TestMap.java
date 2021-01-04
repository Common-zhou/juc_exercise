package com.beiwu.ch5;

import com.beiwu.ch1.tools.SleepTools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoubing
 * @date 2021-01-04 21:58
 * 测试HashMap的基本api  测试线程不安全案例
 */
public class TestMap {
    private static HashMap<String, String> shareMap = new HashMap<>();

    private static class InsertHashMapRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                String key = String.valueOf(i);
                String value = Thread.currentThread().getName() + "_" + i;
                //System.out.println(Thread.currentThread().getName() + " put key:" + key + "   value:" + value);
                shareMap.put(key, value);
                SleepTools.ms(1);
                //System.out.println(Thread.currentThread().getName() + " get key:" + key + "   value:" + shareMap.get(key));
            }
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String key1Once = map.put("key1", "test1");
        String key1Twice = map.put("key1", "test2");
        // 插入相同的key  返回旧值

        System.out.println("insert does't exist key:" + key1Once);
        System.out.println("insert exist key:" + key1Twice);

        // 测试多线程读写map  线程不安全

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new InsertHashMapRunnable());
            thread.start();
        }

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    String s = shareMap.get(j + "");
                    System.out.println(s);
                    SleepTools.ms(1);
                }
            });
            thread.start();
        }

        System.out.println("all thread has inited success!");


    }
}
