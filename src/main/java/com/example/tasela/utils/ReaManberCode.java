package com.example.tasela.utils;

import com.alibaba.druid.sql.visitor.functions.Char;

import java.util.Random;
import java.util.UUID;

/**
 * @author jinmos
 * @date 2019-09-17 11:09
 * 生成随机字符串
 */
public class ReaManberCode {


    /**
     * token生成
     * @param length
     * @return
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }

        Random randGen = new Random();

        char[]  numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

        char [] randBuffer = new char[length];

        for (int i=0; i<randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(64)];
        }
        return new String(randBuffer);
    }

    /**
     * 生成UUID
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("","-");
    }

    /**
     * 生成四位随机字符串
     * @return
     */
    public static String getCharCode(){
        String[] strings={"0","1","2","3","4","5","6","7","8","9",
                "Q","W","E","R","T","Y","U","I","O","P","A","S","D","F","G","H","J","K","L","Z","X","C","V","B","N","M"};
        StringBuffer stringBuffer=new StringBuffer();
        for (int i = 0; i < 4 ; i++) {
            String st=strings[new Random().nextInt(strings.length)].toString();
            stringBuffer.append(st);
        }
        return stringBuffer.toString();
    }
}
