package com.beiwu.ch4.templateMethod;

/**
 * @author zhoubing
 * @date 2020-12-27 16:02
 */
public class SmallCake extends Cake {

    private boolean shouldApply;

    public SmallCake(boolean shouldApply) {
        this.shouldApply = shouldApply;
    }

    @Override
    void shape() {
        System.out.println("SmallCake 形状");
    }

    @Override
    void apply() {
        System.out.println("SmallCake 涂抹");
    }

    @Override
    void bake() {
        System.out.println("SmallCake 烘焙");
    }

    @Override
    protected boolean shouldApply() {
        return this.shouldApply;
    }
}
