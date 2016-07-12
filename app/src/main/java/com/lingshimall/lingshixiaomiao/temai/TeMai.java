package com.lingshimall.lingshixiaomiao.temai;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
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
    private final static int PAGE_SIZE_NUM = 10;
    private int tmz_Cur_Num = 1;
    private int jjks_Cur_Num = 1;
    //    boolean isLastRow = false;
    private CustomTMZListViewAdapter customTMZListViewAdapter;
    private CustomJSKSListViewAdapter customJSKSListViewAdapter;
    private ArrayList<PullToRefreshListView> views;
    private ArrayList<TMZGoods> tmzGoodses;
    private ArrayList<JJKSGoods> jjksGoodses;
    private PullToRefreshListView listViewTMZ;
    private PullToRefreshListView listViewJJKS;
    private TextView nowTimeTV;

    //handler线程间通信
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (tmz_Cur_Num == 1) {
                    tmzGoodses = (ArrayList<TMZGoods>) msg.obj;
                    setAdapterForTMZ();
                } else {
                    ArrayList<TMZGoods> newtmzGoodses = (ArrayList<TMZGoods>) msg.obj;
                    if (newtmzGoodses != null) {
                        tmzGoodses.addAll(newtmzGoodses);
                        customTMZListViewAdapter.notifyDataSetChanged();
                    }
                    listViewTMZ.onRefreshComplete();
                }

            } else if (msg.what == 1) {
                if (jjks_Cur_Num == 1) {
                    jjksGoodses = (ArrayList<JJKSGoods>) msg.obj;
                    setAdapterForJJKS();
                } else {
                    ArrayList<JJKSGoods> newjjksGoodses = (ArrayList<JJKSGoods>) msg.obj;
                    if (newjjksGoodses != null) {
                        jjksGoodses.addAll(newjjksGoodses);
                        customJSKSListViewAdapter.notifyDataSetChanged();
                    }
                    listViewJJKS.onRefreshComplete();
                }
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
        //给ListView添加 上拉+下拉  刷新监听
        addListenerForListView();
        return view;
    }

    //给ListView添加 上拉+下拉  刷新监听
    private void addListenerForListView() {

        //给ListView添加 下拉+上拉  加载监听
        listViewTMZ.setOnRefreshListener(new TheRLForListViewTMZ1());
        listViewJJKS.setOnRefreshListener(new TheRLForListViewJJKS());
    }

    public class TheRLForListViewTMZ1 implements PullToRefreshBase.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshBase refreshView) {
            if (refreshView.isShownHeader()) {

                String nowTime = DateUtils.formatDateTime(
                        getActivity(),
                        System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                                | DateUtils.FORMAT_SHOW_DATE
                                | DateUtils.FORMAT_ABBREV_ALL);
                refreshView.getLoadingLayoutProxy()
                        .setLastUpdatedLabel(nowTime);


                String pathURL = URLs.TEMAIZHONG + "&" + URLs.PG_CUR + "=" + 1 + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

                new RequestDataThreadForTMZ(pathURL).start();

            } else if (refreshView.isShownFooter()) {

                tmz_Cur_Num += 1;
                String pathURL = URLs.TEMAIZHONG + "&" + URLs.PG_CUR + "=" + tmz_Cur_Num + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;
                new RequestDataThreadForTMZ(pathURL).start();
            }
        }
    }

    //给ListView添加 下拉 +上拉  监听
    public class TheRLForListViewTMZ implements PullToRefreshBase.OnRefreshListener2 {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            String nowTime = DateUtils.formatDateTime(
                    getActivity(),
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                            | DateUtils.FORMAT_SHOW_DATE
                            | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy()
                    .setLastUpdatedLabel(nowTime);
//            nowTimeTV.setText(nowTime);
            tmz_Cur_Num = 1;

            String pathURL = URLs.TEMAIZHONG + "&" + URLs.PG_CUR + "=" + tmz_Cur_Num + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

            new RequestDataThreadForTMZ(pathURL).start();
//            ToastUtil.showToast(getActivity(), "11111111111" + nowTime);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            tmz_Cur_Num = tmz_Cur_Num + 1;

            String pathURL = URLs.TEMAIZHONG + "&" + URLs.PG_CUR + "=" + tmz_Cur_Num + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

            new RequestDataThreadForTMZ(pathURL).start();
        }
    }

    public class TheRLForListViewJJKS1 implements PullToRefreshBase.OnRefreshListener {

        @Override
        public void onRefresh(PullToRefreshBase refreshView) {
            String nowTime = DateUtils.formatDateTime(
                    getActivity(),
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                            | DateUtils.FORMAT_SHOW_DATE
                            | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy()
                    .setLastUpdatedLabel(nowTime);
//            nowTimeTV.setText(nowTime);

            String pathURL = URLs.JIJIANGKAISHI + "&" + URLs.PG_CUR + "=" + 1 + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

            new RequestDataThreadForJJKS(pathURL).start();
//            ToastUtil.showToast(getActivity(), "2222222222222" + nowTime);
        }
    }

    //给ListView添加 下拉 +上拉  监听
    public class TheRLForListViewJJKS implements PullToRefreshBase.OnRefreshListener2 {

        @Override
        public void onPullDownToRefresh(PullToRefreshBase refreshView) {
            String nowTime = DateUtils.formatDateTime(
                    getActivity(),
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                            | DateUtils.FORMAT_SHOW_DATE
                            | DateUtils.FORMAT_ABBREV_ALL);
            refreshView.getLoadingLayoutProxy()
                    .setLastUpdatedLabel(nowTime);
//            nowTimeTV.setText(nowTime);
            String pathURL = URLs.JIJIANGKAISHI + "&" + URLs.PG_CUR + "=" + 1 + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

            new RequestDataThreadForJJKS(pathURL).start();
//            ToastUtil.showToast(getActivity(), "2222222222222" + nowTime);
        }

        @Override
        public void onPullUpToRefresh(PullToRefreshBase refreshView) {
            jjks_Cur_Num += 1;
            String pathURL = URLs.JIJIANGKAISHI + "&" + URLs.PG_CUR + "=" + jjks_Cur_Num + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

            new RequestDataThreadForJJKS(pathURL).start();
        }
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

    //右上角购物车点击监听
    private void addListenerForGouwuche() {
        gouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "+++购物车+++", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //geiviewpager添加监听事件
    private void addListenerForViewPager() {
        viewPager.setOnPageChangeListener(new ThePageChangeListener());
    }

    //给“热卖中”“即将开始”添加监听事件
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
        View viewTMZ = new View(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewTMZ.setLayoutParams(params);
        ArrayList<View> viewlist = new ArrayList<>();
        listViewTMZ = new PullToRefreshListView(context);
        TextView btn_top = new TextView(context);
        viewlist.add(btn_top);
        viewlist.add(listViewTMZ);
        viewTMZ.addChildrenForAccessibility(viewlist);
//      listViewTMZ = (PullToRefreshListView) viewTMZ.findViewById(R.id.ptr_listview_id);
//        TextView btn_top= (TextView) viewTMZ.findViewById(R.id.btn_top_id);

        setbtntopVisible(btn_top);
//        btn_top.setOnClickListener(new TheListenerForTMZbtn());

        View emptyView = getActivity().getLayoutInflater().inflate(R.layout.listview_emptyview_layout, null);
        listViewTMZ.setEmptyView(emptyView);
        //设置上拉 下拉 双模式
        listViewTMZ.setMode(PullToRefreshBase.Mode.BOTH);
//        listViewTMZ.setOnScrollListener(new TheScrollListenerForPtrl());
        listViewTMZ.setOnItemClickListener(new TheItemClickListenerForPtrl());
        listViewTMZ.setDividerDrawable(getResources().getDrawable(R.mipmap.line_address));
        //给 特卖中 页面  开启线程请求数据，并发送到 handler shezhi
        String pathURL = URLs.TEMAIZHONG + "&" + URLs.PG_CUR + "=" + 1 + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;

        new RequestDataThreadForTMZ(pathURL).start();
        views.add(listViewTMZ);
    }

    //请求数据的线程<特卖中>
    public class RequestDataThreadForTMZ extends Thread {
        private int cur_Num;
        private int page_Num;
        private String pathURL;

        public RequestDataThreadForTMZ(String path) {
            this.pathURL = path;
        }

        @Override
        public void run() {
            super.run();
            //请求到的 特卖中 数据
//            ToastUtil.showToast(getActivity(), tmz_Cur_Num + "");
            byte[] jsonStr = TeMaiUtils.getJsonStrData(pathURL);
            ArrayList<TMZGoods> goodsTMZ = TeMaiUtils.getTMZListFromJsonStr(jsonStr);
            Message msg = handler.obtainMessage();
            msg.what = 0;
            msg.obj = goodsTMZ;
            msg.sendToTarget();
        }
    }

    public class RequestDataThreadForJJKS extends Thread {
        private String pathURL;

        public RequestDataThreadForJJKS(String path) {

            this.pathURL = path;
        }

        @Override
        public void run() {
            super.run();
//            ToastUtil.showToast(getActivity(), jjks_Cur_Num + "");
            byte[] jsonStr = TeMaiUtils.getJsonStrData(pathURL);
            ArrayList<JJKSGoods> goodsJJKS = TeMaiUtils.getJJKSListFromJsonStr(jsonStr);
            Message msg = handler.obtainMessage();
            msg.what = 1;
            msg.obj = goodsJJKS;
            msg.sendToTarget();
        }
    }

    private void setAdapterForTMZ() {
        customTMZListViewAdapter = new CustomTMZListViewAdapter(tmzGoodses, getActivity());
        listViewTMZ.setAdapter(customTMZListViewAdapter);
        customTMZListViewAdapter.notifyDataSetChanged();
        listViewTMZ.onRefreshComplete();
    }

    private void setAdapterForJJKS() {
        customJSKSListViewAdapter = new CustomJSKSListViewAdapter(jjksGoodses, getActivity());
        listViewJJKS.setAdapter(customJSKSListViewAdapter);
        customJSKSListViewAdapter.notifyDataSetChanged();
        listViewJJKS.onRefreshComplete();
    }

    //给 ViewPager 添加 即将开始 页面
    private void addJSKSListViewToVP() {
//        View viewJJKS=getActivity().getLayoutInflater().inflate(R.layout.temai_listview_btn_layout,null);
//        listViewJJKS = (PullToRefreshListView) viewJJKS.findViewById(R.id.ptr_listview_id);
//        TextView btn_top= (TextView) viewJJKS.findViewById(R.id.btn_top_id);
//        setbtntopVisible(btn_top);
//        btn_top.setOnClickListener(new TheListenerForJJKSbtn());

        listViewJJKS = new PullToRefreshListView(context);
        View viewJJKS = new View(context);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewJJKS.setLayoutParams(params);
//        listViewJJKS.setOnScrollListener(new TheScrollListenerForPtrl());
        listViewJJKS.setOnItemClickListener(new TheItemClickListenerForPtrl());
//设置上拉 下拉 双模式
        listViewJJKS.setMode(PullToRefreshBase.Mode.BOTH);
        View emptyView = getActivity().getLayoutInflater().inflate(R.layout.listview_emptyview_layout, null);
        listViewJJKS.setEmptyView(emptyView);

        listViewJJKS.setDividerDrawable(getResources().getDrawable(R.mipmap.line_address));
        String pathURL = URLs.JIJIANGKAISHI + "&" + URLs.PG_CUR + "=" + 1 + "&" + URLs.PG_SIZE + "=" + PAGE_SIZE_NUM;
        new RequestDataThreadForJJKS(pathURL).start();
        views.add(listViewJJKS);
    }

    private void setbtntopVisible(TextView btn) {
        btn.setClickable(true);
        btn.setHeight(30);
        btn.setWidth(30);
        btn.setRight(15);
        btn.setBottom(15);
        btn.setBackground(getResources().getDrawable(R.mipmap.btn_top));
        if (listViewTMZ.getRefreshableView().getFirstVisiblePosition() >= 3) {
            btn.setVisibility(View.VISIBLE);
        } else {
            btn.setVisibility(View.GONE);
        }
    }

    //listview 单条点击监听
    public class TheItemClickListenerForPtrl implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getActivity(), "跳转到详情页" + position, Toast.LENGTH_SHORT).show();
        }
    }

    private class TheListenerForTMZbtn implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            listViewJJKS.getRefreshableView().smoothScrollToPosition(0);
        }
    }

    private class TheListenerForJJKSbtn implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            listViewJJKS.getRefreshableView().smoothScrollToPosition(0);
        }
    }
    /*    //listview分页加载
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
        }*/

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

