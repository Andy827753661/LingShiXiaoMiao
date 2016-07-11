package com.lingshimall.lingshixiaomiao.activitys;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.MainActivity;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.fragments.MyCatFragment;

public class RegisteLogin_2_Activity extends AppCompatActivity {

    private EditText registe_checknum, registe_password_1, registe_password_2;

    private TextView countime;

    private Button registe;

    private TimeCount timeco = new TimeCount(120000, 1000);

    String temp = "";
    String phonenum = "";

    String checknum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe_login_2_);
        initView();

        timeco.start();
    }

    private void initView() {
        registe_checknum = (EditText) findViewById(R.id.registe_checknum);
        registe_password_1 = (EditText) findViewById(R.id.registe_password_1);
        registe_password_2 = (EditText) findViewById(R.id.registe_password_2);
        countime = (TextView) findViewById(R.id.countime);
        registe = (Button) findViewById(R.id.registe);
    }

    public void clicked(View view) {

        Intent intent = getIntent();
        String checknum = intent.getStringExtra("msg");

        if (!registe_checknum.getText().toString().equals(checknum)) {
            Toast.makeText(RegisteLogin_2_Activity.this, "激活码不正确！", Toast.LENGTH_LONG).show();
        }else if (!registe_password_1.getText().toString().equals(registe_password_1.getText().toString())){
            Toast.makeText(RegisteLogin_2_Activity.this, "密码不一致！", Toast.LENGTH_LONG).show();
        }else {
            Intent intentMine=new Intent(RegisteLogin_2_Activity.this, MainActivity.class);
            String phoneNum=intent.getStringExtra("phoneNum");
            intentMine.putExtra("phoneNum",phoneNum);
            intentMine.putExtra("checked",true);

            Toast.makeText(RegisteLogin_2_Activity.this, "注册成功！", Toast.LENGTH_LONG).show();
            timeco.cancel();
            startActivity(intentMine);
            finish();
        }

    }


    /* 定义一个倒计时的内部类 */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            temp = "";
            Toast.makeText(RegisteLogin_2_Activity.this, "超时，需要重新发送激活码！", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示

            countime.setText(millisUntilFinished / 1000 + "秒");
        }
    }
}
