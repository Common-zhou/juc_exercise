package com.beiwu.ch2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhoubing
 * @date 2020-12-25 17:06
 */
public class MakeArray {
    private static final int ARRAY_SIZE = 100;
    public static List<Integer> makeArray(){
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            result.add(random.nextInt(ARRAY_SIZE * 3));
        }
        return result;
    }
}
