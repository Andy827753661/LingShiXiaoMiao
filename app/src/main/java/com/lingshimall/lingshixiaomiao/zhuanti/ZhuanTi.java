package com.lingshimall.lingshixiaomiao.zhuanti;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.lingshimall.lingshixiaomiao.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 张 波 on 2016/7/6.
 */
public class ZhuanTi extends Fragment {

    private View view;
    private GridView gridView;
    private ListView listView;

    private  ArrayList<HashMap<String, Object>> listItems;

    private String[] item = {"日韩", "欧美", "台湾", "韩国"};

    public ZhuanTi() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhuanti_layout, null);

        gridView = (GridView) view.findViewById(R.id.zhuanti_gv_id);
        listView = (ListView) view.findViewById(R.id.zhuanti_lv_id);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        aboutGridView();
        aboutListView();
    }

    private void aboutListView() {

    }

    private void aboutGridView() {
        listItems = new ArrayList<HashMap<String, Object>>();
        //将数组信息分别存入ArrayList中
        int len = item.length;
        for (int i = 0; i < len; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("item", item[i]);
            listItems.add(map);
        }

        gridView.setAdapter(new ZhuantiGridAdapter(listItems,getActivity()));

    }
}
