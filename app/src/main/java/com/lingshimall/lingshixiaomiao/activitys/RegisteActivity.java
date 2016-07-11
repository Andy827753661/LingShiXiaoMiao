package com.lingshimall.lingshixiaomiao.activitys;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisteActivity extends AppCompatActivity {

    private Button registe_then_bt,registe_login_bt;

    private EditText registe_phonenum;

    private String temp="";
    private String phonenum="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);

        registe_then_bt= (Button) findViewById(R.id.registe_then_bt);
        registe_login_bt= (Button) findViewById(R.id.registe_login_bt);

        registe_phonenum= (EditText) findViewById(R.id.registe_phonenum);
        registe_then_bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for(int i=0;i<5;i++){//产生一个五位数的激活码
                    int k=(int) (Math.random()*10);
                    temp+=k;
                }

                phonenum=registe_phonenum.getText().toString().trim();
                SmsManager smsmanger=SmsManager.getDefault();
                if(isPhoneNumberValid(phonenum)){
                    PendingIntent mPI= PendingIntent.getBroadcast(RegisteActivity.this, 0, new Intent(), 0);
                    smsmanger.sendTextMessage(phonenum, null, "你的激活码是："+temp, mPI, null);
                    Toast.makeText(RegisteActivity.this, "激活码发送成功!", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(RegisteActivity.this,RegisteLogin_2_Activity.class);
                    intent.putExtra("msg", temp);
                    intent.putExtra("phoneNum",phonenum);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(RegisteActivity.this, "电话格式不正确,请检查！", Toast.LENGTH_LONG).show();
                }
                if(!isPhoneNumberValid(phonenum)){
                    Toast.makeText(RegisteActivity.this, "电话格式不正确,请检查！", Toast.LENGTH_LONG).show();
                }
                else if(phonenum.length()==0){
                    Toast.makeText(RegisteActivity.this, "请输入电话号码！", Toast.LENGTH_LONG).show();
                }




            }


        });


    }

    /*检查字符串是否为电话号码的方法,并回传true or false的判断值*/
    public static boolean isPhoneNumberValid(String mobiles){
        Matcher m = null;
        if(mobiles.trim().length()>0){
            Pattern p = Pattern.compile("1[3|5|7|8|][0-9]{9}");
            m= p.matcher(mobiles);
        }
        else{

            return false;
        }
        return m.matches();
    }

    //跳转到登录界面
    public void registLogin(View view){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
