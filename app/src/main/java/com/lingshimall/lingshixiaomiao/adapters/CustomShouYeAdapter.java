package com.lingshimall.lingshixiaomiao.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/12.
 */
public class CustomShouYeAdapter extends PagerAdapter {

    private ArrayList<View> viewsList;

    public CustomShouYeAdapter(ArrayList<View> viewsList) {
        this.viewsList = viewsList;
    }

    @Override
    public int getCount() {
        return viewsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View iv = viewsList.get(position);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewsList.get(position));
    }
}
