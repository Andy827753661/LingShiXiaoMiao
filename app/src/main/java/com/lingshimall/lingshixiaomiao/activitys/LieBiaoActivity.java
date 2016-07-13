package com.lingshimall.lingshixiaomiao.activitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lingshimall.lingshixiaomiao.BaseActivity;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.adapters.LieBiaoGridAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LieBiaoActivity extends BaseActivity {


    private GridView gridView;

    private  ArrayList<HashMap<String, Object>> gridItems;

    private String[] item = {"日韩", "欧美", "台湾", "韩国"};

    private String list_jsonStr;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie_biao);

        gridView= (GridView) findViewById(R.id.zhuanti_liebiao_gv);

        gridItems = new ArrayList<HashMap<String, Object>>();
        //将数组信息分别存入ArrayList中
        int len = item.length;
        for (int i = 0; i < len; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("item", item[i]);
            gridItems.add(map);
        }
        gridView.setAdapter(new LieBiaoGridAdapter());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(LieBiaoActivity.this, XiangQingActivity.class);
                startActivity(intent);
            }
        });


    }
}
