package com.lingshimall.lingshixiaomiao.activitys;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.fragments.MyCatAboutOrderFragment;
import com.lingshimall.lingshixiaomiao.wo.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCatAboutOrder extends AppCompatActivity {
    private HorizontalScrollView hs;
    private LinearLayout linearLayout;
    private ViewPager viewPager;
    private String[] titles = { "全部", "代付款", "待发货", "待收货", "待评价"};
    private List<TextView> textViews = new ArrayList<TextView>();
    private List<Fragment> fragments = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cat_about_order);
        initView();




    }

    private void initView() {
        // TODO Auto-generated method stub
        hs = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        linearLayout = (LinearLayout) findViewById(R.id.myorder_hs_linearLayout);
        viewPager = (ViewPager) findViewById(R.id.myorder_viewPager);
        initTab();
        initViewPager();
    }

    /**
     * 初始化标签
     */
    private void initTab() {

        for (int i = 0; i < titles.length; i++) {

            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            llp.weight = 1;
            llp.setMargins(10, 10, 10, 10);
            TextView view = new TextView(this);
            view.setText(titles[i]);
            view.setTextSize(20);
            view.setTextColor(Color.BLACK);
            view.setGravity(Gravity.CENTER);
            view.setEnabled(true);
            view.setLayoutParams(llp);
            linearLayout.addView(view);


            view.setTag(i);

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO ViewPager切换
                    viewPager.setCurrentItem((Integer) v.getTag(), false);
                }
            });
            textViews.add(view);

        }
        textViews.get(0).setEnabled(false);

    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        for (int i = 0; i < titles.length; i++) {
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
}
