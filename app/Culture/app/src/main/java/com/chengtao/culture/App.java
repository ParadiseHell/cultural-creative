package com.chengtao.culture;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.chengtao.library.CrashHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by ChengTao on 2016-12-20.
 */

public class App extends Application{
    private static Map<String, Activity> destoryMap = new HashMap<String, Activity>();
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    /**
     * 添加到销毁队列
     *
     * @param activity 要销毁的activity
     */

    public static void addDestoryActivity( String activityName,Activity activity) {
        if (!destoryMap.containsKey(activityName)){
            destoryMap.put(activityName, activity);
        }
    }

    /**
     * 销毁指定Activity
     */
    public static void destoryActivity(String activityName) {
        Set<String> keySet = destoryMap.keySet();
        for (String key : keySet) {
            if (key.equals(activityName)){
                destoryMap.get(key).finish();
            }
        }
    }

    /**
     * 获取指定Activity
     */
    public static Activity getActivity(String activityName) {
        return destoryMap.get(activityName);
    }
}
