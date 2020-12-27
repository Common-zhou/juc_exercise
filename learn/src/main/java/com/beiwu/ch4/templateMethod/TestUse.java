package com.beiwu.ch4.templateMethod;

/**
 * @author zhoubing
 * @date 2020-12-27 16:03
 */
public class TestUse {
    public static void main(String[] args) {
        Cake cake = new CheeseCake();
        cake.make();

        Cake cake1 = new SmallCake(false);
        cake1.make();
    }
}
