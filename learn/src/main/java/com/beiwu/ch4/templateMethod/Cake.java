package com.beiwu.ch4.templateMethod;

/**
 * @author zhoubing
 * @date 2020-12-27 15:59
 * 做蛋糕的模板类
 */
public abstract class Cake {
    //蛋糕形状
    abstract void shape();

    //蛋糕涂抹
    abstract void apply();

    //烘焙
    abstract void bake();

    void make() {
        shape();
        if (shouldApply()) {
            apply();
        }
        bake();
    }

    // 可以布置这种钩子方法  让程序更加灵活
    protected boolean shouldApply() {
        return true;
    }

}
