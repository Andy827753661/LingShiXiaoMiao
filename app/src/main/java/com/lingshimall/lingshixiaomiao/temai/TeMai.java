package com.lingshimall.lingshixiaomiao.temai;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import com.lingshimall.lingshixiaomiao.adapters.CustomListViewAdapter;
import com.lingshimall.lingshixiaomiao.adapters.CustomViewPagerAdapter;
import com.lingshimall.lingshixiaomiao.beans.Goods;
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
//    @ViewInject(R.id.temai_vp_id)
//    private ViewPager viewPager;

    private ArrayList<PullToRefreshListView> views;

    public TeMai() {
    }

/*    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = getActivity();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }*/

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
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
    private void initViewPager() {

        initViewPagerData();
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
        views = new ArrayList<PullToRefreshListView>();
        for (int i = 0; i < 2; i++) {
            PullToRefreshListView listView = new PullToRefreshListView(context);
            View emptyView=getActivity().getLayoutInflater().inflate(R.layout.listview_emptyview_layout,null);
            listView.setEmptyView(emptyView);
            initListViewData(listView);
            listView.setOnScrollListener(new TheScrollListenerForPtrl());
            listView.setOnItemClickListener(new TheItemClickListenerForPtrl());

            views.add(listView);
        }
    }

    public class RequestDataThread extends Thread{

    }

    private void initListViewData(PullToRefreshListView listView) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] jsonStr= TeMaiUtils.getJsonStrData(URLs.TEMAIZHONG);
                ArrayList<Goods> goods=TeMaiUtils.getListFromJsonStr(jsonStr);
                Log.e("TAG===>", "run:goods.size() "+goods.size() );
            }
        }).start();

        ArrayList<Goods> goodses = new ArrayList<>();
        for (int i = 0; i <= 50; i++) {
            Goods good = new Goods();



            //添加数据
            goodses.add(good);
            listView.setDividerDrawable(getResources().getDrawable(R.mipmap.line_address));
            CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(goodses, getActivity());
            listView.setAdapter(customListViewAdapter);
        }
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
    }

