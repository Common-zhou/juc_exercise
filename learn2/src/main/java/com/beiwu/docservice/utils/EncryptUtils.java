package com.beiwu.docservice.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author zhoubing
 * @Date 2021-07-15 15:26
 */
public class EncryptUtils {
    public static String encryptStr(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        }
        return strDes;
    }


    /**
     *
     * @param str 需要被加密的字符串
     * @return 对字符串str进行SHA-1加密后，将加密字符串返回
     *
     */
    public static String encryptBySHA1(String str) {
        return encryptStr(str, "SHA-1");
    }

    /**
     *
     * @param str 需要被加密的字符串
     * @return 对字符串str进行SHA-256加密后，将加密字符串返回
     *
     */
    public static String encryptBySHA256(String str) {
        return encryptStr(str, "SHA-256");
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }


    public static void main(String[] args) {
        String sha256 = EncryptUtils.encryptBySHA256("hello world");
        System.out.println(sha256.length());
    }
}
