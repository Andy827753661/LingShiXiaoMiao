package com.lingshimall.lingshixiaomiao.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.TMZGoods;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/7.
 */
public class CustomTMZListViewAdapter extends BaseAdapter {
    private ArrayList<TMZGoods> goodses;
    private Activity activity;

    public CustomTMZListViewAdapter(ArrayList<TMZGoods> goodses, Activity activity) {
        this.goodses = goodses;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return goodses.size();
//        Log.e("getCount===》", "getCount: "+goodses.size());
    }

    @Override
    public TMZGoods getItem(int position) {
        Log.e("getCount===》", "getCount: " + position);
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
            convertView = activity.getLayoutInflater().from(activity).inflate(R.layout.listview_item_tmz_layout, null);

            holder.picture = (ImageView) convertView.findViewById(R.id.list_picture_id);
            holder.addcar = (TextView) convertView.findViewById(R.id.list_addcar_id);
            holder.discount = (TextView) convertView.findViewById(R.id.list_discount_id);
            holder.jianyishoujia = (TextView) convertView.findViewById(R.id.list_jianyishoujia_id);
            holder.name = (TextView) convertView.findViewById(R.id.list_name_id);
            holder.time = (TextView) convertView.findViewById(R.id.list_time_id);
            holder.price = (TextView) convertView.findViewById(R.id.list_price_id);
            holder.percent = (TextView) convertView.findViewById(R.id.list_percent_id);
            holder.yiqiang = (TextView) convertView.findViewById(R.id.list_yiqiang_id);
            holder.progress = (ProgressBar) convertView.findViewById(R.id.list_progress_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TMZGoods good = goodses.get(position);
        holder.name.setText(good.getTitle());
        holder.time.setText(good.getTime() + "");
        holder.price.setText(good.getPrice().getCurrent() + "");
        holder.jianyishoujia.setText(good.getPrice().getPrime() + "");
        holder.discount.setText(good.getTag().getTitle());
        holder.progress.setProgress(good.getSpecial_percentage());
        holder.yiqiang.setText("已抢" + good.getSpecial_num() + "件");
        holder.percent.setText("剩余" + good.getSpecial_percentage() + "%");
        Picasso.with(activity).load(good.getImg().getImg_url()).into(holder.picture);
        holder.addcar.setOnClickListener(new TheClickListenerForAddcar());
        return convertView;

    }

    public class TheClickListenerForAddcar implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Log.e("ClickListenerForAddcar", "onClick==加入购物车！！！ ");
            Toast.makeText(activity, "加入购物车！", Toast.LENGTH_SHORT).show();
        }
    }

    private class ViewHolder {
        ImageView picture;
        TextView discount, name, time, price, jianyishoujia, percent, addcar, yiqiang;
        ProgressBar progress;
    }
}
