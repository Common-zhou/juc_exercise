package com.geetest.c_017_dp;

/**
 * @author zhoubing
 * @date 2020-05-07 19:53
 */
public class StrategyDemo {
    static Operation operation;
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 2;

        operation = new AddOperation();
        System.out.println("num1 + num2 = " + operation.operation(num1, num2));

        operation = new DecendOperation();
        System.out.println("num1 - num2 = " + operation.operation(num1, num2));

        operation = new MultiOperation();
        System.out.println("num1 * num2 = " + operation.operation(num1, num2));

    }
}
