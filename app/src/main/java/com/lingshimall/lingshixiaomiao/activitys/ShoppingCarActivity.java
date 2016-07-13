package com.lingshimall.lingshixiaomiao.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.BaseActivity;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.ShoppingCar;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCarActivity extends BaseActivity {

    private ListView shoppingcar_lv;

    private List<ShoppingCar> list;

    private String userName;
    private View view;
    private TextView priceTextView;
    private CheckBox allcheckBox;
    private Button bianjiButton, jiesuanButton;
    public static String sum = "";

    public static List<Boolean> booleans = new ArrayList<Boolean>();
    
    public Handler handler = new Handler() {
        public void handleMessage(Message message) {
            String string = "";
            switch (message.what) {
                case 1:
                    // 接受handler穿过来的item选中后的商品的价格进行判断
                    sum = message.obj.toString();
                    priceTextView.setText(sum + "");
                    break;
                case 2:
                    // 接受handler穿过来的item选中的状态进行判断
                    @SuppressWarnings("unchecked")
                    List<Boolean> array = (List<Boolean>) message.obj;
                    booleans.clear();
                    booleans.addAll(array);
                    int count = 0;
                    for (int i = 0; i < array.size(); i++) {
                        if (array.get(i) == true) {
                            count++;
                        }
                    }
                    if (count == list.size()) {
                        allcheckBox.setChecked(true);
                    } else {
                        allcheckBox.setChecked(false);
                    }
                    break;
                case 3:
                    string = (String) message.obj;
                    bianjiButton.setText(string);
                    break;
                case 4:
                    string = (String) message.obj;
                    jiesuanButton.setText(string);
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_car);

        shoppingcar_lv= (ListView) findViewById(R.id.shopping_listview);

//        Intent intent=getIntent();
//        userName=intent.getStringExtra("userName");
//
//        ShoppingMessage shoppingMessage=new ShoppingMessage();
//        list=shoppingMessage.getShopping(userName,this);
//
//        ShoppingCarAdapter adapter=new ShoppingCarAdapter(this,list,handler);
//
//        shoppingcar_lv.setAdapter(adapter);

    }
}
