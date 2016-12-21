package com.chengtao.culture;

import android.app.Application;
import android.content.Context;

import com.chengtao.library.CrashHandler;

/**
 * Created by ChengTao on 2016-12-20.
 */

public class App extends Application{
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
