package com.beiwu.concurrent;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @Author zhoubing
 * @Date 2021-07-14 17:20
 */
public class GuavaBloom {
    public static void main(String[] args) {
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 1500);

        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));

        bloomFilter.put(1);
        bloomFilter.put(3);
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));

    }
}
