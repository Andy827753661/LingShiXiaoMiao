package com.lingshimall.lingshixiaomiao.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.lingshimall.lingshixiaomiao.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    //跳转到注册界面
    public void phonenumRegiste(View view){
        Intent intent=new Intent(this, RegisteActivity.class);
        startActivity(intent);
        finish();

    }
}
