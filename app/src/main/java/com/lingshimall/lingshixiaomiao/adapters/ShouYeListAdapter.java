package com.lingshimall.lingshixiaomiao.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.ShangPin;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/12.
 */
public class ShouYeListAdapter extends BaseAdapter {

    private ArrayList<ShangPin> shangPinList;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public ShouYeListAdapter(Activity activity, ArrayList<ShangPin> list) {
        this.layoutInflater = activity.getLayoutInflater();
        this.shangPinList = list;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return shangPinList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ShangPin getItem(int position) {
        return shangPinList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.first_top, null);

            viewHolder.name = (TextView) convertView.findViewById(R.id.shouyelist_name_id);
            viewHolder.price = (TextView) convertView.findViewById(R.id.shouyelist_price_id);
            viewHolder.picture = (ImageView) convertView.findViewById(R.id.shouyelist_picture_id);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ShangPin shangpin = shangPinList.get(position);

        viewHolder.name.setText(shangpin.getGoods());
        viewHolder.price.setText(shangpin.getPrice() + "元");
        Log.e("PRICE===>", "getView: " + shangpin.getPrice());
        Picasso.with(activity).load(shangpin.getPicture()).into(viewHolder.picture);

        return convertView;
    }


    class ViewHolder {
        TextView name, price;
        ImageView picture;
    }
}
