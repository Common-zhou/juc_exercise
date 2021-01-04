package com.beiwu.ch5.bitwise;

/**
 * @author zhoubing
 * @date 2020-12-29 23:49
 * 位运算
 */
public class IntToBinary {
    public static void main(String[] args) {
        // 100
        System.out.println("the 4 is : " + Integer.toBinaryString(4));
        // 110
        System.out.println("the 6 is : " + Integer.toBinaryString(6));

        // 100
        System.out.println("the 4&6 is :" + Integer.toBinaryString(4 & 6));
        //110
        System.out.println("the 4|6 is :" + Integer.toBinaryString(4 | 6));

        // 1111 1111 1111 1111 1111 1111 1111 1011
        System.out.println("the ~4 is :" + Integer.toBinaryString(~4));

        // 010
        System.out.println("the 4^6 is :" + Integer.toBinaryString(4 ^ 6));

        // 010
        System.out.println("the 4>>1 is :" + Integer.toBinaryString(4>>1));

        // 1000
        System.out.println("the 4<<1 is :" + Integer.toBinaryString(4<<1));
        System.out.println("the 7<<1 is :" + Integer.toBinaryString(7<<1));

        System.out.println("the 234567 is : " + Integer.toBinaryString(234567));
        System.out.println("the 234567>>>4 is : " + Integer.toBinaryString(234567>>>4));


        System.out.println("the 345 % 16 is : " + (345%16)+" or "+(345&(16-1)));

        System.out.println("Mark hashCode is : "+"Mark".hashCode()+"="
                +Integer.toBinaryString("Mark".hashCode()));
        System.out.println("Bill hashCode is : "+"Bill".hashCode()+"="
                +Integer.toBinaryString("Bill".hashCode()));
    }
}
