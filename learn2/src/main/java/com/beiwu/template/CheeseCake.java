package com.beiwu.template;

/**
 * @author zhoubing
 * @date 2021-06-29 18:25
 */
public class CheeseCake extends MakeCake {
    @Override
    protected void shape() {
        System.out.println("芝士蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("芝士蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("芝士蛋糕烘焙");
    }
}
