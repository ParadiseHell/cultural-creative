package com.chengtao.culture.utils;

/**
 * Created by ChengTao on 2016-12-21.
 */

public class StringUtils {
    public static boolean isStrNull(String... str){
        for (String s : str){
            if (s == null || s.equals("")){
                return true;
            }
        }
        return false;
    }
}
