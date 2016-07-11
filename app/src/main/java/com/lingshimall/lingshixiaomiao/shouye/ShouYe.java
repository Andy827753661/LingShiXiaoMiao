package com.lingshimall.lingshixiaomiao.shouye;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.fragments.BlogsListView;

import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


/**
 * Created by 张 波 on 2016/7/6.
 */
public class ShouYe extends Fragment {
    private View view;
    private BlogsListView  listView;
    private ViewPager  vp_welcome_id;
    private LinearLayout  ll_container_id;
    private List<View> ds;

    public ShouYe() {

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shouye_layout, container, false);
        listView=(BlogsListView) view.findViewById(R.id.lv_id);
        MyAdapter adapter=new MyAdapter(getActivity());
        listView.setAdapter(adapter);
        View view1=inflater.inflate(R.layout.shouye_top,null);

        vp_welcome_id = (ViewPager) view1.findViewById(R.id.vp_welcome_id);
        ll_container_id = (LinearLayout) view1.findViewById(R.id.ll_container_id);

        aboutDots();
        aboutViewPager();


        listView.addHeaderView(view1);
        listView.setAdapter(adapter);
        return view;
    }

    private void aboutDots() {


        MyOnClickListener listener = new MyOnClickListener();


        for (int i = 0; i < ll_container_id.getChildCount(); i++) {

            ImageView iv = (ImageView) ll_container_id.getChildAt(i);
            iv.setEnabled(true);
            iv.setOnClickListener(listener);
            iv.setTag(i);

        }

        ll_container_id.getChildAt(0).setEnabled(false);

    }

    private final class MyOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {

            vp_welcome_id.setCurrentItem((Integer) (v.getTag()));

        }

    } private void aboutViewPager() {
        int[] imageIds = new int[]{R.mipmap.imag_001, R.mipmap.imag_002, R.mipmap.imag_003, R.mipmap.imag_004};
        ds = new LinkedList<>();
        for (int imageId : imageIds) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(imageId);
            ds.add(iv);

        }

        PagerAdapter adapter = new MyPagerAdapter();

        vp_welcome_id.setAdapter(adapter);

        vp_welcome_id.setOnPageChangeListener(new MyOnPageChangeListener());

    }


    private final class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {

            for (int i = 0; i < ll_container_id.getChildCount(); i++) {

                ImageView iv = (ImageView) ll_container_id.getChildAt(i);

                iv.setEnabled(true);
            }


            ll_container_id.getChildAt(position).setEnabled(false);

        }


        @Override
        public void onPageScrollStateChanged(int state) {


        }

    }

    private final class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {

            return ds.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View iv = ds.get(position);

            container.addView(iv);

            return iv;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


            container.removeView(ds.get(position));
        }
    }







}

class  MyAdapter extends BaseAdapter{
    Context context;

    public MyAdapter(Context context){
        this.context=context;
    }

    @Override
    public int getCount() {
        return 50;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.first_top, null);

            // 初始化ViewHolder
            viewHolder = new ViewHolder();

            viewHolder.textView = (TextView) convertView
                    .findViewById(R.id.text_1);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.photo_1);

            convertView.setTag(viewHolder);

        } else  {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.textView.setText("介绍介绍");

        }







        return convertView;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    class ViewHolder {
        TextView textView;
        ImageView  imageView;
    }

}
