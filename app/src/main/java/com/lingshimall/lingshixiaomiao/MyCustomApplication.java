package com.lingshimall.lingshixiaomiao;

import android.app.Application;

import org.xutils.x;

/**
 * Created by 张 波 on 2016/7/9.
 */
public class MyCustomApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);

    }
}
