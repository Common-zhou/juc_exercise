package com.beiwu.ch2.forkjoin.sum;

import com.beiwu.ch1.tools.SleepTools;

/**
 * @author zhoubing
 * @date 2020-12-24 23:37
 */
public class SumNormal {
    public static Long sum(int[] src){
        long sum = 0;
        for (int i = 0; i < src.length; i++) {
            SleepTools.ms(1);
            sum += src[i];
        }
        return sum;
    }
}
