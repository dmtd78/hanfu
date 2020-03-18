package com.dmtd.hanfu.forum.util;

/**
 * Copyright (c) 3/18/2020 All Rights Reserved By SUNWIN
 * <p>
 * Package: com.dmtd.hanfu.forum.util
 * FileName: TokenEncryptUtils
 *
 * @author duanmin
 * @description
 * @created 3/18/2020-8:54 AM
 * @last Modified
 * @history
 */
public class TokenEncryptUtils {
    // 编码密码,可自定义
    private static final String ENCODED_PASSWORD = "dmtd";

    /**
     * 编码
     *
     * @param str
     * @return
     */
    public static String encoded(String str) {
        return strToHex(encodedString(str, ENCODED_PASSWORD));
    }

    /**
     * 转换
     *
     * @param str
     * @param password
     * @return
     */
    private static String encodedString(String str, String password) {
        char[] pwd = password.toCharArray();
        int pwdLen = pwd.length;

        char[] strArray = str.toCharArray();
        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = (char) (strArray[i] ^ pwd[i % pwdLen] ^ pwdLen);
        }
        return new String(strArray);
    }

    private static String strToHex(String s) {
        return bytesToHexStr(s.getBytes());
    }

    private static String bytesToHexStr(byte[] bytesArray) {
        StringBuilder builder = new StringBuilder();
        String hexStr;
        for (byte bt : bytesArray) {
            hexStr = Integer.toHexString(bt & 0xFF);
            if (hexStr.length() == 1) {
                builder.append("0");
                builder.append(hexStr);
            } else {
                builder.append(hexStr);
            }
        }
        return builder.toString();
    }

    /**
     * 解码
     *
     * @param str
     * @return
     */
    public static String decoded(String str) {
        String hexStr = null;
        try {
            hexStr = hexStrToStr(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hexStr != null) {
            hexStr = encodedString(hexStr, ENCODED_PASSWORD);
        }
        return hexStr;
    }

    private static String hexStrToStr(String hexStr) {
        return new String(hexStrToBytes(hexStr));
    }

    private static byte[] hexStrToBytes(String hexStr) {
        String hex;
        int val;
        byte[] btHexStr = new byte[hexStr.length() / 2];
        for (int i = 0; i < btHexStr.length; i++) {
            hex = hexStr.substring(2 * i, 2 * i + 2);
            val = Integer.valueOf(hex, 16);
            btHexStr[i] = (byte) val;
        }
        return btHexStr;
    }

}
