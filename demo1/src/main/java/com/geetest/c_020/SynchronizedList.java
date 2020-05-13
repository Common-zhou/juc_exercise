package com.geetest.c_020;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author zhoubing
 * @date 2020-05-13 09:13
 */
public class SynchronizedList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collection<String> synchronizedCollection = Collections.synchronizedCollection(list);
    }
}
