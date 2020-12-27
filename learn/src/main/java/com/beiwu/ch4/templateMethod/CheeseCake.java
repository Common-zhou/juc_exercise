package com.beiwu.ch4.templateMethod;

/**
 * @author zhoubing
 * @date 2020-12-27 16:01
 */
public class CheeseCake extends Cake {
    @Override
    void shape() {
        System.out.println("Cheese 形状");
    }

    @Override
    void apply() {
        System.out.println("Cheese 涂抹");
    }

    @Override
    void bake() {
        System.out.println("Cheese 烘焙");
    }
}
