package com.lingshimall.lingshixiaomiao.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.Goods;

import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/7.
 */
public class CustomListViewAdapter extends BaseAdapter {
    private ArrayList<Goods> goodses;
    private LayoutInflater layoutInflater;

    public CustomListViewAdapter(ArrayList<Goods> goodses, Activity activity) {
        this.goodses = goodses;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return goodses.size();
    }

    @Override
    public Goods getItem(int position) {
        return goodses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.listview_item_layout, null);
            holder.textView = (TextView) convertView.findViewById(R.id.list_item_tv_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.textView.setText(goodses.get(position).getGoodName());
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView textView;
    }
}
