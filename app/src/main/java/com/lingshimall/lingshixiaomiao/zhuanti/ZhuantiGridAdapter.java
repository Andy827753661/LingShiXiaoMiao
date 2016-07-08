package com.lingshimall.lingshixiaomiao.zhuanti;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by zhai on 2016/7/7.
 */
public class ZhuantiGridAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, Object>> listItems;
    private Context context;

    private TextView zhuanti_grid_item_tv_country, zhuanti_grid_item_tv_describ;

    private ImageView zhuanti_grid_item_tv_iv;

    private Integer[] imgs = {R.mipmap.icon_rihan,R.mipmap.icon_oumei,R.mipmap.icon_taiwan,R.mipmap.icon_hanguo};

    public ZhuantiGridAdapter(ArrayList<HashMap<String, Object>> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.zhuanti_grid_item, null);

            zhuanti_grid_item_tv_iv = (ImageView) convertView.findViewById(R.id.zhuanti_grid_item_tv_iv);
            convertView.setTag(zhuanti_grid_item_tv_iv);
        } else {
            zhuanti_grid_item_tv_iv = (ImageView) convertView.getTag();
        }
        zhuanti_grid_item_tv_iv.setImageResource(imgs[position]);

        return convertView;
    }
}
