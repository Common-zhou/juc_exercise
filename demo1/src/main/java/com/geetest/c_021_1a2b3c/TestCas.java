package com.geetest.c_021_1a2b3c;

/**
 * @author zhoubing
 * @date 2020-05-13 11:57
 */
public class TestCas {
    public enum ReadyToRun {
        T1, T2
    }

    public static volatile ReadyToRun readyToRun = ReadyToRun.T1;

    public static void main(String[] args) {
        char[] numberChars = "123456".toCharArray();
        char[] alphaChars = "ABCDEF".toCharArray();

        new Thread(() -> {
            for (int i = 0; i < numberChars.length; i++) {
                while (readyToRun != ReadyToRun.T1) {
                }
                System.out.println(numberChars[i]);
                readyToRun = ReadyToRun.T2;
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < alphaChars.length; i++) {
                while (readyToRun != ReadyToRun.T2) {
                }
                System.out.println(alphaChars[i]);
                readyToRun = ReadyToRun.T1;
            }
        }).start();
    }
}
