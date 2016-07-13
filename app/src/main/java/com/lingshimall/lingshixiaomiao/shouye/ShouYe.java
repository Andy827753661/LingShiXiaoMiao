package com.lingshimall.lingshixiaomiao.shouye;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.activitys.XiangQingActivity;
import com.lingshimall.lingshixiaomiao.adapters.CustomShouYeAdapter;
import com.lingshimall.lingshixiaomiao.adapters.ShouYeListAdapter;
import com.lingshimall.lingshixiaomiao.beans.JJKSGoods;
import com.lingshimall.lingshixiaomiao.beans.ShangPin;
import com.lingshimall.lingshixiaomiao.beans.URLs;
import com.lingshimall.lingshixiaomiao.fragments.BlogsListView;
import com.lingshimall.lingshixiaomiao.utils.ShouYeUtils;

import java.util.ArrayList;


/**
 * Created by 张 波 on 2016/7/6.
 */
public class ShouYe extends Fragment {
    private View main_view;
    private BlogsListView listView;
    private ViewPager shouye_viewpager;
    private LinearLayout shouye_container_id;
    private ArrayList<ShangPin> shangPinsList;
    private View headview;
    private ArrayList<View> viewList;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                shangPinsList = (ArrayList<ShangPin>) msg.obj;
                if (shangPinsList != null) {
                    //给listview添加Adapter
                    setAdapterForListview();
                } else {
                    Toast.makeText(getActivity(), "shangPinsList为KONG", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    public ShouYe() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_view = inflater.inflate(R.layout.fragment_shouye_layout, container, false);
        listView = (BlogsListView) main_view.findViewById(R.id.shouye_listview_id);
        //添加加载视图
        View emptyView = getActivity().getLayoutInflater().from(getActivity()).inflate(R.layout.listview_emptyview_layout, null);
        listView.setEmptyView(emptyView);
        //给listview的Adapter请求数据
        new RequestDataForListView().start();
        //渲染出头部view，并添加到listview
        creatANDaddViewToListView();

        aboutDots();

  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(),"点击事件",Toast.LENGTH_SHORT).show();
          if (view != null) {
              int jjksId =  shangPinsList.get((position)-1).getShangpin_id();
              Intent intent = new Intent(getActivity(), XiangQingActivity.class);
              Bundle bundle = new Bundle();
              bundle.putInt("GoodId", jjksId);
              intent.putExtras(bundle);
              startActivity(intent);
          } else {
              Toast.makeText(getActivity(), "网络阻塞，请刷新", Toast.LENGTH_SHORT).show();

          }


      }
  });




        return main_view;
    }

    private void creatANDaddViewToListView() {
        headview = getActivity().getLayoutInflater().from(getActivity()).inflate(R.layout.shouye_top, null);
        shouye_viewpager = (ViewPager) headview.findViewById(R.id.shouye_viewpager_id);
          ImageView  img  =(ImageView)  headview.findViewById(R.id.qiqi_id);
         ImageView photto=(ImageView) headview.findViewById(R.id.photto);
          img.setOnClickListener(new OnClickListener() {
              @Override
              public void onClick(View v) {
                  Toast.makeText(getActivity(),"新的Activity",Toast.LENGTH_SHORT).show();
              }
          });
         photto.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(getActivity(),"此处省略1000000000000000000000000000字",Toast.LENGTH_SHORT).show();
             }
         });


        creatDataForVP();
        setAdapterForVP();
        shouye_viewpager.setOnPageChangeListener(new MyOnPageChangeListener());
        shouye_container_id = (LinearLayout) headview.findViewById(R.id.shouye_container_id);

        listView.addHeaderView(headview);

    }

    private void setAdapterForVP() {
        CustomShouYeAdapter viewpagerAdapter = new CustomShouYeAdapter(viewList);
        shouye_viewpager.setAdapter(viewpagerAdapter);
    }

    private void creatDataForVP() {
        int[] imgIDs = new int[]{R.mipmap.imag_001, R.mipmap.imag_002, R.mipmap.imag_003, R.mipmap.imag_004};
        viewList = new ArrayList<>();
        for (int imgid : imgIDs) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(imgid);
            viewList.add(imageView);
        }


    }

    private class RequestDataForListView extends Thread {
        @Override
        public void run() {
            super.run();
            byte[] data = ShouYeUtils.getJsonStrData(URLs.SHOUYEURL);
            ArrayList<ShangPin> spList = ShouYeUtils.getDataFromJson(data);
            Message msg = handler.obtainMessage();
            msg.what = 0;
            msg.obj = spList;
            msg.sendToTarget();
        }
    }

    private void setAdapterForListview() {
        ShouYeListAdapter listAdapter = new ShouYeListAdapter(getActivity(), shangPinsList);
        listView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    private void aboutDots() {
        MyOnClickListener listener = new MyOnClickListener();
        for (int i = 0; i < shouye_container_id.getChildCount(); i++) {

            ImageView iv = (ImageView) shouye_container_id.getChildAt(i);
            iv.setEnabled(true);
            iv.setOnClickListener(listener);
            iv.setTag(i);
        }
        shouye_container_id.getChildAt(0).setEnabled(false);

    }

    private final class MyOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {

            shouye_viewpager.setCurrentItem((Integer) (v.getTag()));

        }

    }

    private class MyOnPageChangeListener implements OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {

            for (int i = 0; i < shouye_container_id.getChildCount(); i++) {
                ImageView iv = (ImageView) shouye_container_id.getChildAt(i);
                iv.setEnabled(true);
            }
            shouye_container_id.getChildAt(position).setEnabled(false);

        }
        @Override
        public void onPageScrollStateChanged(int state) {
        }

    }

}






