package com.lingshimall.lingshixiaomiao.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.JJKSGoods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/7.
 */
public class CustomJSKSListViewAdapter extends BaseAdapter {
    private ArrayList<JJKSGoods> goodses;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public CustomJSKSListViewAdapter(ArrayList<JJKSGoods> goodses, Activity activity) {
        this.goodses = goodses;
        this.activity = activity;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return goodses.size();
    }

    @Override
    public JJKSGoods getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.listview_item_jsks_layout, null);
            holder.picture = (ImageView) convertView.findViewById(R.id.list_picture_id);
            holder.name = (TextView) convertView.findViewById(R.id.list_name_id);
            holder.discount = (TextView) convertView.findViewById(R.id.list_discount_id);
            holder.jianyishoujia = (TextView) convertView.findViewById(R.id.list_jianyishoujia_id);
            holder.kaiqiangshijian = (TextView) convertView.findViewById(R.id.list_kaiqiangshijian_id);
            holder.price = (TextView) convertView.findViewById(R.id.list_price_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        JJKSGoods good = goodses.get(position);

        holder.name.setText(good.getTitle());
        holder.price.setText(good.getPrice().getCurrent() + "");
        holder.jianyishoujia.setText(good.getPrice().getPrime() + "");
        holder.kaiqiangshijian.setText(good.getTime() + "");
        holder.discount.setText(good.getTag().getTitle());
        Picasso.with(activity).load(good.getImg().getImg_url()).into(holder.picture);
        return convertView;
    }
    private class ViewHolder {
        ImageView picture;
        TextView discount, name, price, jianyishoujia, kaiqiangshijian;
    }
}
