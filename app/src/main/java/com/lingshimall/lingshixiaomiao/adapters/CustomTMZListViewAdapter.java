package com.lingshimall.lingshixiaomiao.adapters;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 张 波 on 2016/7/7.
 */
public class CustomTMZListViewAdapter extends BaseAdapter {
    private ArrayList<TMZGoods> goodses;
    private Activity activity;
    private TimeHandler handler;

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
//        holder.time.setText(good.getTime() + "");
        handler = new TimeHandler(holder.time, good.getTime());
        handler.obtainMessage(2).sendToTarget();
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

            Toast.makeText(activity, "成功加入购物车", Toast.LENGTH_SHORT).show();
        }
    }

    private class TimeHandler extends Handler {
        private TextView timeView;
        private int mTime;

        public TimeHandler(TextView timeView, int time) {
            this.timeView = timeView;
            this.mTime = time;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2) {
                String t = CustomTMZListViewAdapter.getDateToString(mTime);
                timeView.setText(t);
                new TheTimeThread(mTime).start();
            }
        }
    }

    public class TheTimeThread extends Thread {
        int mTime;

        public TheTimeThread(int time) {
            this.mTime = mTime;
        }

        @Override
        public void run() {
            super.run();
            mTime = mTime - 1000;
            if (mTime > 0) {
                Message message = handler.obtainMessage(2);
                handler.sendMessageDelayed(message, 1000);
            }
        }
    }

    public static String getDateToString(long time) {
        Date d = new Date(time * 100L);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd日HH时mm分ss秒");
        return simpleDateFormat.format(d);
    }

    private class ViewHolder {
        ImageView picture;
        TextView discount, name, time, price, jianyishoujia, percent, addcar, yiqiang;
        ProgressBar progress;
    }
}

