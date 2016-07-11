package com.lingshimall.lingshixiaomiao.temai;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.adapters.CustomJSKSListViewAdapter;
import com.lingshimall.lingshixiaomiao.adapters.CustomTMZListViewAdapter;
import com.lingshimall.lingshixiaomiao.adapters.CustomViewPagerAdapter;
import com.lingshimall.lingshixiaomiao.beans.JJKSGoods;
import com.lingshimall.lingshixiaomiao.beans.TMZGoods;
import com.lingshimall.lingshixiaomiao.beans.URLs;
import com.lingshimall.lingshixiaomiao.utils.TeMaiUtils;

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
    private final static int EACH_PAGE=15;
    private int last_Item_Index;
    private int page_Num=0;
    boolean isLastRow = false;
    private CustomTMZListViewAdapter customTMZListViewAdapter;
    private CustomJSKSListViewAdapter customJSKSListViewAdapter;
    private ArrayList<PullToRefreshListView> views;
    private ArrayList<TMZGoods> tmzGoodses;
    private ArrayList<JJKSGoods> jjksGoodses;
    private PullToRefreshListView listViewTMZ;
    private PullToRefreshListView listViewJJKS;

    //handler线程间通信
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                tmzGoodses = (ArrayList<TMZGoods>) msg.obj;
                Log.e("tmzGoodses.size()==>", "run: " + tmzGoodses.size());
                setAdapterForTMZ(tmzGoodses);
            } else if (msg.what == 1) {
                jjksGoodses = (ArrayList<JJKSGoods>) msg.obj;
                Log.e("jjksGoodses.size()==>", "run: " + jjksGoodses.size());
                setAdapterForJJKS(jjksGoodses);
            }
        }
    };

    //构造方法
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
        //初始化控件 findviewbyId
        initView();
        //初始化 viewpager  ：添加page
        initViewPager();
        //给右上角的购物车 添加监听
        addListenerForGouwuche();
        //给 “特卖中”“即将开始” 添加监听
        addListenerForTextView();
        //给viewpager 添加监听
        addListenerForViewPager();
        return view;
    }

    //初始化 viewpager  ：添加page
    private void initViewPager() {
        views = new ArrayList<PullToRefreshListView>();
        //初始化 特卖中Page，并添加到ViewPager中
        addTMZListViewToVP();
        //初始化 即时开始Page，并添加到Viewpager中
        addJSKSListViewToVP();

        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(views);
        viewPager.setAdapter(customViewPagerAdapter);
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
        return 0;
    }

    public class ThePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            switch (position) {
                case 0:
                    textViews.get(0).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    textViews.get(1).setBackgroundColor(getResources().getColor(R.color.colorBlack));
                    break;
                case 1:
                    textViews.get(1).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    textViews.get(0).setBackgroundColor(getResources().getColor(R.color.colorBlack));
                    break;
                default:
                    textViews.get(0).setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    textViews.get(1).setBackgroundColor(getResources().getColor(R.color.colorBlack));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    //给 ViewPager 添加 特卖中 页面
    private void addTMZListViewToVP() {
        listViewTMZ = new PullToRefreshListView(context);

        View emptyView = getActivity().getLayoutInflater().inflate(R.layout.listview_emptyview_layout, null);
        listViewTMZ.setEmptyView(emptyView);

        listViewTMZ.setOnScrollListener(new TheScrollListenerForPtrl());
        listViewTMZ.setOnItemClickListener(new TheItemClickListenerForPtrl());
        listViewTMZ.setDividerDrawable(getResources().getDrawable(R.mipmap.line_address));
        //给 特卖中 页面  开启线程请求数据，并发送到 handler shezhi
        new RequestDataThreadForTMZ().start();
        views.add(listViewTMZ);
    }

    //请求数据的线程<特卖中>
    public class RequestDataThreadForTMZ extends Thread {
        @Override
        public void run() {
            super.run();
            //请求到的 特卖中 数据
            byte[] jsonStr = TeMaiUtils.getJsonStrData(URLs.TEMAIZHONG);
            ArrayList<TMZGoods> goodsTMZ = TeMaiUtils.getTMZListFromJsonStr(jsonStr);
            Log.e("TAG==goods.size()==>", "run: " + goodsTMZ.size());
            Message msg = handler.obtainMessage();
            msg.what = 0;
            msg.obj = goodsTMZ;
            msg.sendToTarget();
        }
    }

    public class RequestDataThreadForJJKS extends Thread {
        @Override
        public void run() {
            super.run();
            byte[] jsonStr = TeMaiUtils.getJsonStrData(URLs.JIJIANGKAISHI);
            ArrayList<JJKSGoods> goodsJJKS = TeMaiUtils.getJJKSListFromJsonStr(jsonStr);
            Log.e("TAG==goods.size()==>", "run: " + goodsJJKS.size());
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = goodsJJKS;
            msg.sendToTarget();
        }
    }

    private void setAdapterForTMZ(ArrayList<TMZGoods> goods) {
        customTMZListViewAdapter = new CustomTMZListViewAdapter(goods, getActivity());
        listViewTMZ.setAdapter(customTMZListViewAdapter);
    }

    private void setAdapterForJJKS(ArrayList<JJKSGoods> goods) {
        customJSKSListViewAdapter = new CustomJSKSListViewAdapter(goods, getActivity());
        listViewJJKS.setAdapter(customJSKSListViewAdapter);
    }

    //给 ViewPager 添加 即将开始 页面
    private void addJSKSListViewToVP() {
        listViewJJKS = new PullToRefreshListView(context);
        listViewJJKS.setOnScrollListener(new TheScrollListenerForPtrl());
        listViewJJKS.setOnItemClickListener(new TheItemClickListenerForPtrl());

        View emptyView = getActivity().getLayoutInflater().inflate(R.layout.listview_emptyview_layout, null);
        listViewJJKS.setEmptyView(emptyView);

        listViewJJKS.setDividerDrawable(getResources().getDrawable(R.mipmap.line_address));

        new RequestDataThreadForJJKS().start();
        views.add(listViewJJKS);
    }

    //listview 单条点击监听
    public class TheItemClickListenerForPtrl implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "跳转到详情页" + position, Toast.LENGTH_SHORT).show();
        }
    }

        //listview分页加载
        public class TheScrollListenerForPtrl implements AbsListView.OnScrollListener {

            //第1次：scrollState = SCROLL_STATE_TOUCH_SCROLL(1) 正在滚动
            //第2次：scrollState = SCROLL_STATE_FLING(2) 手指做了抛的动作（手指离开屏幕前，用力滑了一下）
            //第3次：scrollState = SCROLL_STATE_IDLE(0) 停止滚动
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (isLastRow && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    Toast.makeText(getActivity(), "开始刷新数据" , Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount > 0) {
                    isLastRow = true;
                }
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
    }

