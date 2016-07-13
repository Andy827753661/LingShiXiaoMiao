package com.lingshimall.lingshixiaomiao.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.lingshimall.lingshixiaomiao.R;

public class LieBiaoActivity extends AppCompatActivity {


    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lie_biao);

        gridView= (GridView) findViewById(R.id.zhuanti_liebiao_gv);



    }
}
