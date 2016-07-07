package com.lingshimall.lingshixiaomiao.temai;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.adapters.CustomListViewAdapter;
import com.lingshimall.lingshixiaomiao.adapters.CustomViewPagerAdapter;
import com.lingshimall.lingshixiaomiao.beans.Goods;

import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/6.
 */
public class TeMai extends Fragment {

    private View view;
    private Context context;
    private ViewPager viewPager;
    private TextView temaizhong, jijiangkaishi;
    private ArrayList<TextView> textViews;
    private ImageView gouwuche;
//    @ViewInject(R.id.temai_vp_id)
//    private ViewPager viewPager;

    private ArrayList<ListView> views;

    public TeMai() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_temai_layout, container, false);
//        ViewUtils.inject(view);
        initView();
        initViewPager();
        addListenerForGouwuche();
        addListenerForTextView();
        addListenerForViewPager();
        return view;
    }

    private void addListenerForGouwuche() {
        gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "+++购物车+++", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addListenerForViewPager() {
        viewPager.setOnPageChangeListener(new ThePageChangeListener());
    }

    private void addListenerForTextView() {
        temaizhong.setOnClickListener(new TheTextViewListener());
        jijiangkaishi.setOnClickListener(new TheTextViewListener());
    }


    public class TheTextViewListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            //方法二
            viewPager.setCurrentItem(whichPage(v, textViews));


            //方法一
           /* int id=v.getId();
            switch (id){
                case R.id.temai_temaizhong_id:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.temai_jijiangkaishi_id:
                    viewPager.setCurrentItem(1);
                    break;
            }*/
        }
    }

    private int whichPage(View v, ArrayList<TextView> textViews) {
        for (int i = 0; i < textViews.size(); i++) {
            if (v == textViews.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public class ThePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            switch (position) {
                case 0:
                    temaizhong.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    jijiangkaishi.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                    break;
                case 1:
                    jijiangkaishi.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    temaizhong.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                    break;
                default:
                    temaizhong.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    jijiangkaishi.setBackgroundColor(getResources().getColor(R.color.colorBlack));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private void initView() {
        viewPager = (ViewPager) view.findViewById(R.id.temai_vp_id);
        temaizhong = (TextView) view.findViewById(R.id.temai_temaizhong_id);
        jijiangkaishi = (TextView) view.findViewById(R.id.temai_jijiangkaishi_id);
        gouwuche = (ImageView) view.findViewById(R.id.temai_gouwuche_id);

        textViews = new ArrayList<>();
        textViews.add(temaizhong);
        textViews.add(jijiangkaishi);

        temaizhong.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        jijiangkaishi.setBackgroundColor(getResources().getColor(R.color.colorBlack));
    }

    private void initViewPagerData() {
        views = new ArrayList<ListView>();
        for (int i = 0; i < 2; i++) {
            ListView listView = new ListView(context);
            initListViewData(listView);
            views.add(listView);
        }

    }

    private void initListViewData(ListView listView) {
        ArrayList<Goods> goodses = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            Goods good = new Goods("" + i, "第" + i + "个Good", "" + i, "" + i, "" + i, "" + i, "" + i, "" + i);
            goodses.add(good);
            CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(goodses, getActivity());
            listView.setAdapter(customListViewAdapter);
        }
    }

    private void initViewPager() {

        initViewPagerData();
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(views);
        viewPager.setAdapter(customViewPagerAdapter);
    }
}
