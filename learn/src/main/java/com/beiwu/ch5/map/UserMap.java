package com.beiwu.ch5.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhoubing
 * @date 2021-01-04 22:24
 * chm put
 *  不存在的key值 返回null
 *  存在的key值 返回旧值 新值存入
 *
 * putIfAbsent
 *      如果当前key不存在 则存入 返回null
 *      如果当前key存在 则不做任何操作 返回旧值
 */
public class UserMap {
    public static void main(String[] args) {
        ConcurrentHashMap<KeyVo, String> map = new ConcurrentHashMap<>();
        KeyVo keyVo = new KeyVo(1,"A");
        System.out.println("put不存在的值-------");
        System.out.println(map.put(keyVo, "AA"));
        System.out.println(map.get(keyVo));

        System.out.println("put已存在的值------");
        System.out.println(map.put(keyVo, "BB"));
        System.out.println(map.get(keyVo));

        System.out.println("putIfAbsent已存在的值-------");
        System.out.println(map.putIfAbsent(keyVo, "CC"));
        System.out.println(map.get(keyVo));

        KeyVo keyVo1 = new KeyVo(2,"C");
        System.out.println(map.putIfAbsent(keyVo1, "AAA"));
        System.out.println(map.get(keyVo1));
    }
}
