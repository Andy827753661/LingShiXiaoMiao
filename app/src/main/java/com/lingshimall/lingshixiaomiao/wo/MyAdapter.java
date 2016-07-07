package com.lingshimall.lingshixiaomiao.wo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by zhai on 2016/7/7.
 */
public class MyAdapter extends FragmentPagerAdapter {

    List<Fragment> list;


    public MyAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }
    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

}
