package com.geetest.c_016_gc;

import java.util.Arrays;

/**
 * @author zhoubing
 * @date 2020-05-07 19:32
 */
public class TestSort {
    public static void main(String[] args) {
        Cat cat1 = new Cat(12, 16, "yellow");
        Cat cat2 = new Cat(13, 15, "blue");
        Cat cat3 = new Cat(14, 14, "grey");
        Cat cat4 = new Cat(15, 13, "black");
        Cat cat5 = new Cat(16, 12, "white");

        Cat[] cats = {cat1, cat2, cat3, cat4, cat5};

        Arrays.sort(cats, (a, b) -> a.getWeight() - b.getWeight());
        System.out.println(Arrays.toString(cats));

        Arrays.sort(cats, (a, b) -> a.getHeight() - b.getHeight());
        System.out.println(Arrays.toString(cats));

    }
}
