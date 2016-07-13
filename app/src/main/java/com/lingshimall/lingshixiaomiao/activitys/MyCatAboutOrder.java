package com.lingshimall.lingshixiaomiao.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.fragments.MyCatAboutOrderFragment;
import com.lingshimall.lingshixiaomiao.adapters.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCatAboutOrder extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    private RadioGroup radioGroup;
    private RadioButton[] buttons;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cat_about_order);
        initView();
        initGroupView();
        initViewPager();


    }

    private void initView() {
       // TODO Auto-generated method stub
        viewPager = (ViewPager) findViewById(R.id.myorder_viewPager);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

//
    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.myorder_viewPager);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            MyCatAboutOrderFragment fragment = new MyCatAboutOrderFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("key", i);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }

        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                buttons[position].setChecked(true);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void initGroupView() {
        // TODO Auto-generated method stub

        buttons = new RadioButton[radioGroup.getChildCount()];
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            buttons[i] = (RadioButton) radioGroup.getChildAt(i);
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                for (int i = 0; i < radioGroup.getChildCount(); i++) {
                    if (buttons[i].getId() == checkedId) {
                        // TODO 切换ViewPager
                        viewPager.setCurrentItem(i,false);

                    }
                }
            }
        });
    }

}
