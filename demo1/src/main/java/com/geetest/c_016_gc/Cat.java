package com.geetest.c_016_gc;

/**
 * @author zhoubing
 * @date 2020-05-07 19:32
 */
public class Cat {
    private int height;
    private int weight;
    private String color;

    public Cat() {
    }

    public Cat(int height, int weight, String color) {
        this.height = height;
        this.weight = weight;
        this.color = color;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "height=" + height +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
