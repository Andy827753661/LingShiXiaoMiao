package com.lingshimall.lingshixiaomiao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.ZhuanTiModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by zhai on 2016/7/12.
 */
public class ZhuanTiListViewAdapter  extends BaseAdapter{

    private Context context;
    private ArrayList<ZhuanTiModel> list;

    public ZhuanTiListViewAdapter(Context context, ArrayList<ZhuanTiModel> list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView==null){
            holder=new ViewHolder();
            convertView =LayoutInflater.from(context).inflate(R.layout.zhuanti_list_item,null);

            holder.zhuanti_list_item_iv= (ImageView) convertView.findViewById(R.id.zhuanti_list_item_iv);
            holder.zhuanti_list_item_like= (ImageView) convertView.findViewById(R.id.zhuanti_list_item_like);
            holder.zhuanti_list_item_tv= (TextView) convertView.findViewById(R.id.zhuanti_list_item_tv);
            holder.zhuanti_list_item_like_tv= (TextView) convertView.findViewById(R.id.zhuanti_list_item_like_tv);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ZhuanTiModel zhuanTiModel=list.get(position);

        Picasso.with(context).load(zhuanTiModel.getImg_url()).into(holder.zhuanti_list_item_iv);
        holder.zhuanti_list_item_tv.setText(zhuanTiModel.getDesc());
        holder.zhuanti_list_item_like_tv.setText(zhuanTiModel.getHotindex()+"");


        return convertView;
    }

   private class ViewHolder{
      ImageView zhuanti_list_item_iv;
       TextView zhuanti_list_item_tv;
       ImageView zhuanti_list_item_like;
       TextView zhuanti_list_item_like_tv;
    }
}
