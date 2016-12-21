package com.chengtao.culture.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.chengtao.culture.App;
import com.chengtao.culture.entity.User;

/**
 * Created by ChengTao on 2016-12-20.
 */

/**
 * SharedPreferences工具类
 */
@SuppressWarnings("EmptyCatchBlock")
public class SpUtils {
    //----------HOST
    private static final String HOST_SP = "HOST_SP";
    private static final String SP_HOST = "SP_HOST";
    //----------USER
    private static final String USER_SP = "USER_SP";
    private static final String SP_USER_NAME = "SP_USER_NAME";
    private static final String SP_USER_PASSWORD = "SP_USER_PASSWORD";
    /**
     * 保存用户信息
     * @param user 用户实体
     */
    public static void saveUser(User user){
        try {
            SharedPreferences preferences = (App.getContext())
                    .getSharedPreferences(USER_SP,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SP_USER_NAME,user.getName());
            editor.putString(SP_USER_PASSWORD,user.getPassword());
            editor.apply();
        }catch (Exception e){

        }
    }

    /**
     * 获取用户
     * @return 用户名
     */
    public static String getUserName(){
        try {
            SharedPreferences preferences = App.getContext()
                    .getSharedPreferences(USER_SP,Context.MODE_PRIVATE);
            return preferences.getString(SP_USER_NAME,"");
        }catch (Exception e){
            return "";
        }
    }
    /**
     * 获取用户密码
     * @return 用户密码
     */
    public static String getUserPassword(){
        try {
            SharedPreferences preferences = App.getContext()
                    .getSharedPreferences(USER_SP,Context.MODE_PRIVATE);
            return preferences.getString(SP_USER_PASSWORD,"");
        }catch (Exception e){
            return "";
        }
    }

    public static void saveHost(String host,String port){
        try {
            SharedPreferences preferences = (App.getContext())
                    .getSharedPreferences(HOST_SP,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(SP_HOST,"http://"+
                    host+
                    ":"+port);
            editor.apply();
        }catch (Exception e){

        }
    }

    /**
     * 获取主机地址
     * @return 主机地址
     */
    public static String getHost(){
        try {
            SharedPreferences preferences = App.getContext()
                    .getSharedPreferences(HOST_SP,Context.MODE_PRIVATE);
            return preferences.getString(SP_HOST,"");
        }catch (Exception e){
            return "";
        }
    }
}
