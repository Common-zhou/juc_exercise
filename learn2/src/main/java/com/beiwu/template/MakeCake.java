package com.beiwu.template;

/**
 * @author zhoubing
 * @date 2021-06-29 18:24
 */
public abstract class MakeCake {

    private boolean apply = true;

    public void make() {
        shape();
        if (this.apply) {
            apply();
        }
        brake();
    }

    public void shouldApply(boolean apply) {
        this.apply = apply;
    }

    protected abstract void shape();

    protected abstract void apply();

    protected abstract void brake();

}
