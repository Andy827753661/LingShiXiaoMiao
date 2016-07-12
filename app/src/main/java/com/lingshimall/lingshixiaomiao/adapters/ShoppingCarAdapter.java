package com.lingshimall.lingshixiaomiao.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.Product;
import com.lingshimall.lingshixiaomiao.beans.ShoppingCar;
import com.lingshimall.lingshixiaomiao.db.ProductMessage;
import com.lingshimall.lingshixiaomiao.wo.DrawArc;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhai on 2016/7/12.
 */
public class ShoppingCarAdapter extends BaseAdapter{

    private Context context;
    public List<ShoppingCar> list;
    // 每个item点击
    private List<Boolean> isSelect = new ArrayList<Boolean>();
    private Handler handler;
    public static List<Boolean> gones = new ArrayList<Boolean>();

    public ShoppingCarAdapter(Context context, List<ShoppingCar> list, Handler handler) {
        super();
        this.context = context;
        this.list = list;
        this.handler = handler;
        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        for (int i = 0; i < list.size(); i++) {
            isSelect.add(false);
            gones.add(true);
        }
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder vHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.shoppingcar_lv_item, null);
            vHolder = new ViewHolder();
            // 复选框
            vHolder.checkBox = (CheckBox) convertView
                    .findViewById(R.id.pro_checkbox);
            // 图片
            vHolder.imageView = (ImageView) convertView
                    .findViewById(R.id.pro_image);
            // +
            vHolder.jiaBox = (CheckBox) convertView.findViewById(R.id.pro_add1);
            // -
            vHolder.jianBox = (CheckBox) convertView
                    .findViewById(R.id.pro_reduce1);
            // 名字
            vHolder.nameTextView = (TextView) convertView
                    .findViewById(R.id.pro_name);
            // 数量
            vHolder.countTextView = (TextView) convertView
                    .findViewById(R.id.pro_count);
            // 价格
            vHolder.priceTextView = (TextView) convertView
                    .findViewById(R.id.pro_shopPrice);

            vHolder.layout = (LinearLayout) convertView
                    .findViewById(R.id.pro_layout);
            convertView.setTag(vHolder);

        } else {

            vHolder = (ViewHolder) convertView.getTag();
        }

        vHolder.countTextView.setText((list.get(position).getCount()) + "");
        vHolder.nameTextView.setText(list.get(position).getName() + "");
        vHolder.priceTextView.setText(list.get(position).getPrice() + "");


        //vHolder.imageView.setImageBitmap(bitmap2);

        vHolder.jiaBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ProductMessage prMessage = new ProductMessage();
                List<Product> datas = prMessage.getProdect(context);
                for (int i = 0; i < datas.size(); i++) {

                            int n = Integer.parseInt(vHolder.countTextView
                                    .getText().toString()) + 1;
                            vHolder.countTextView.setText(String.valueOf(n));
                            list.get(position).setCount(n);
                            String sum = getTotalPrice();
                            Message message = new Message();
                             message.what = 1;
                            message.obj = sum;

                            handler.sendMessage(message);


                }

            }

        });

        vHolder.checkBox
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        // TODO Auto-generated method stub
                        isSelect.set(position, isChecked);
                        vHolder.checkBox.setChecked(isChecked);
                        String sum = getTotalPrice();
                        Message message = new Message();
                        message.obj = sum;
                        message.what = 1;
                        handler.sendMessage(message);

                        Message message1 = new Message();
                        message1.obj = isSelect;
                        message1.what = 2;
                        handler.sendMessage(message1);
                    }
                });
        vHolder.jianBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int n = Integer.parseInt(vHolder.countTextView.getText()
                        .toString());
                n--;
                if (n < 1)
                    n = 1;
                vHolder.countTextView.setText(String.valueOf(n));
                list.get(position).setCount(n);
                String sum = getTotalPrice();
                Message message = new Message();
                message.obj = sum;
                message.what = 1;
                handler.sendMessage(message);
            }
        });

        vHolder.checkBox.setChecked(isSelect.get(position));
        return convertView;
    }

    class ViewHolder {
        CheckBox checkBox, jiaBox, jianBox;
        ImageView imageView;
        TextView nameTextView, countTextView, priceTextView;
        LinearLayout layout;
    }


    private String getTotalPrice() {
        DecimalFormat dif = new DecimalFormat("0.00");

        ShoppingCar shopping = null;
        float totalPrice = 0;
        for (int i = 0; i < list.size(); i++) {
            shopping = list.get(i);
            if (isSelect.get(i)) {
                totalPrice += shopping.getCount() * shopping.getPrice();
            }
        }

        return dif.format(totalPrice);
    }

}
