package com.beiwu.ch2.forkjoin.sum;

import java.util.Random;

/**
 * @author zhoubing
 * @date 2020-12-24 23:17
 */
public class MakeArray {
    public static final int ARRAY_LENGTH = 4000;

    public static int[] makeArray() {
        Random r = new Random();
        int[] result = new int[ARRAY_LENGTH];
        for (int i = 0; i < ARRAY_LENGTH; i++) {
            result[i] = r.nextInt(ARRAY_LENGTH * 3);
        }
        return result;
    }
}
