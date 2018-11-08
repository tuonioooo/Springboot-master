package com.master.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-9-11
 * Time: 14:46
 * info: SHA246算法工具类
 */
public class SHA256Utils {

    /**
     * 将字符串转换SHA256（64位）字符串
     * @param str
     * @param charsetName
     * @return
     */
    public static String getSHA256StrJava(String str, String charsetName){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            //由于UTF-8是多字节编码，需要用多个字节来表示一个字符的编码，所以也就出现了在转换之后byte[]数组长度、内容不一致的情况。
            //而ISO-8859-1编码是单字节编码，所以使用该编码就不会出现上面的问题
            messageDigest.update(str.getBytes(charsetName == null ? "ISO_8859_1": charsetName));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将文件字节流转换SHA256
     * @param bytes
     * @return
     */
    public static String getSHA256StrJava(byte[] bytes){
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bytes);
            encodeStr = byte2HexOfBigInteger(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
 　　* 将byte转为16进制 通过BigInteger转换
 　　* @param bytes
 　　* @return
 　　*/
    private static String byte2HexOfBigInteger(byte[] bytes){
        BigInteger bigInt = new BigInteger(1, bytes);
        return bigInt.toString(16);
    }

    /**
 　　* 将byte转为16进制，通过Integer字节转换
 　　* @param bytes
 　　* @return
 　　*/
    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 将文件进行SHA256加密
     * @param sFile
     * @return
     */
    public static String getFileSHA256(String sFile) {
        File file = new File(sFile);
        if (!file.isFile()){
            System.err.println("not file");
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in=null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }
}
