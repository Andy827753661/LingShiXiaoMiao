package com.lingshimall.lingshixiaomiao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lingshimall.lingshixiaomiao.beans.User;
import com.lingshimall.lingshixiaomiao.db.UserMessage;
import com.lingshimall.lingshixiaomiao.fragments.MyCatFragment;
import com.lingshimall.lingshixiaomiao.shouye.ShouYe;
import com.lingshimall.lingshixiaomiao.temai.TeMai;
import com.lingshimall.lingshixiaomiao.zhuanti.ZhuanTi;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @ViewInject(R.id.main_radiogroup)
    public RadioGroup mian_radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        //向数据库添加
//        User user=new User();
//        user.setUserName("123");
//        user.setPassword("123");
//        user.setImg_url("dshfkjhdslkfhkja");
//        UserMessage userMessage=new UserMessage();
//        userMessage.addUser(this,user);


        x.view().inject(this);
        fragmentManager = getSupportFragmentManager();
        initContent();
        aboutRadioBUttonChecked();

    }

    private void initContent() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_framelayout, new ShouYe());
        fragmentTransaction.commit();
    }

    private void aboutRadioBUttonChecked() {

        mian_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment = null;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.main_radiobutton_home:
                        fragment = new ShouYe();
                        break;
                    case R.id.main_radiobutton_sale:
                        fragment = new TeMai();
                        break;
                    case R.id.main_radiobutton_subject:
                        fragment = new ZhuanTi();
                        break;
                    case R.id.main_radiobutton_mine:
                        fragment = new MyCatFragment();
                        Intent intentLogin=getIntent();
                        String userName=intentLogin.getStringExtra("userName");
                        Bundle args = new Bundle();
                        args.putString("userName",userName);
                        fragment.setArguments(args);
                        break;
                }

                fragmentTransaction.replace(R.id.main_framelayout, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
