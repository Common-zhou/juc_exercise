package com.beiwu.template;

/**
 * @author zhoubing
 * @date 2021-06-29 18:27
 */
public class TestTemplate {

    public static void main(String[] args) {
        //MakeCake cake  = new CheeseCake();
        MakeCake cake  = new CreamCake();

        cake.shouldApply(false);

        cake.make();
    }
}
