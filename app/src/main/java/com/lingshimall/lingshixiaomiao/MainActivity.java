package com.lingshimall.lingshixiaomiao;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lingshimall.lingshixiaomiao.fragments.MyCatFragment;
import com.lingshimall.lingshixiaomiao.shouye.ShouYe;
import com.lingshimall.lingshixiaomiao.temai.TeMai;
import com.lingshimall.lingshixiaomiao.wo.Wo;
import com.lingshimall.lingshixiaomiao.zhuanti.ZhuanTi;

public class MainActivity extends BaseActivity {

    private FragmentManager fragmentManager;

    @ViewInject(R.id.main_radiogroup)
    public RadioGroup mian_radiogroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //启动注解
        ViewUtils.inject(this);
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
                        fragment =new MyCatFragment();
                        break;
                }

                fragmentTransaction.replace(R.id.main_framelayout, fragment);
                fragmentTransaction.commit();
            }
        });
    }
}
