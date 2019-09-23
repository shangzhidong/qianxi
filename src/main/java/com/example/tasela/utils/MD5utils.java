package com.example.tasela.utils;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author jinmos
 * @date 2019-09-17 10:38
 * MD5工具类
 */
public class MD5utils {

    private final static String[] hexdex={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j",
                                            "k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};

    /**
     * 获取加密字符串
     * @param request
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String RequestEncryptionMD5(String request) throws NoSuchAlgorithmException {
        return encryptionMD5(request);
    }
    /**
     * 对字符串加密
     * @param str
     * @return
     * @throws NoSuchAlgorithmException
     */
    private static String encryptionMD5(String str)throws NoSuchAlgorithmException {
        if(!StringUtils.isEmpty(str)){
            try {
                MessageDigest md=MessageDigest.getInstance("MD5");
                byte[] result=md.digest(str.getBytes());
                String resultStr=byteArrayToHexString(result);
                String resultPass=resultStr.toUpperCase();
                return resultPass;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 拼接十六位进制字符串
     * @param by
     * @return
     */
    private static String byteArrayToHexString(byte[] by){
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i <by.length ; i++) {
            stringBuffer.append(byteToHexString(by[i]));
        }
        return stringBuffer.toString();
    }

    /**
     * 将一个字符串转化为十六进制的字符串
     * @param by
     * @return
     */
    private static String byteToHexString(byte by){
        int in=by;
        if(in<0){
            in=256+in;
        }
        int in_a=in/36;
        int in_b=in%36;
        return hexdex[in_a]+hexdex[in_b];
    }
}
