package com.lingshimall.lingshixiaomiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.main_framelayout)
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启用xUtils包中的注解方法
        ViewUtils.inject(this);
    }
}
