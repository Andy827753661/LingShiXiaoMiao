package com.lingshimall.lingshixiaomiao.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.activitys.LoginActivity;
import com.lingshimall.lingshixiaomiao.activitys.MyCatAboutOrder;

/**
 * Created by zhai on 2016/7/7.
 */
public class MyCatFragment extends Fragment {

    private Button mycat_registe;
    private Button mycat_login;
    private RadioGroup mycat_radiogroup;
    private RadioButton mycat_radiobutton_pay;
    private RadioButton mycat_radiobutton_delivery;
    private RadioButton mycat_radiobutton_logistics;
    private RadioButton mycat_radiobutton_evaluate;
    private RelativeLayout myact_Rl_myorder;
    private RelativeLayout myact_Rl_taobaoorder;
    private RelativeLayout mycat_Rl_coupon;
    private RelativeLayout myact_Rl_mine;
    private RelativeLayout mycat_Rl_service;
    private RelativeLayout mycat_Rl_feedback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_my_cat, null);

        mycat_registe= (Button) view.findViewById(R.id.mycat_registe);
        mycat_login= (Button) view.findViewById(R.id.mycat_login);
        mycat_radiogroup= (RadioGroup) view.findViewById(R.id.mycat_radiogroup);
        mycat_radiobutton_pay= (RadioButton) view.findViewById(R.id.mycat_radiobutton_pay);
        mycat_radiobutton_delivery= (RadioButton) view.findViewById(R.id.mycat_radiobutton_delivery);
        mycat_radiobutton_logistics= (RadioButton) view.findViewById(R.id.mycat_radiobutton_logistics);
        mycat_radiobutton_evaluate= (RadioButton) view.findViewById(R.id.mycat_radiobutton_evaluate);
        myact_Rl_myorder= (RelativeLayout) view.findViewById(R.id.mycat_Rl_myorder);
        myact_Rl_taobaoorder= (RelativeLayout) view.findViewById(R.id.mycat_Rl_taobaoorder);
        mycat_Rl_coupon= (RelativeLayout) view.findViewById(R.id.mycat_Rl_coupon);
        myact_Rl_mine= (RelativeLayout) view.findViewById(R.id.mycat_Rl_mine);
        mycat_Rl_service= (RelativeLayout) view.findViewById(R.id.mycat_Rl_service);
        mycat_Rl_feedback= (RelativeLayout) view.findViewById(R.id.mycat_Rl_feedback);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aboutLogin();
        aboutRegiste();
        aboutMycatRadiogroup();
        aboutRelativeLayout();

    }

    //我的订单,淘宝订单,收藏,优惠券,联系客服,意见反馈的整个条目的点击事件
    private void aboutRelativeLayout() {
        TouchListener listener = new TouchListener();
        myact_Rl_myorder.setOnTouchListener(listener);
        myact_Rl_taobaoorder.setOnTouchListener(listener);
        mycat_Rl_coupon.setOnTouchListener(listener);
        myact_Rl_mine.setOnTouchListener(listener);
        mycat_Rl_service.setOnTouchListener(listener);
        mycat_Rl_feedback.setOnTouchListener(listener);
    }

    //我的订单,淘宝订单,收藏,优惠券,联系客服,意见反馈的整个条目的点击事件的操作
    class TouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (v.getId()) {
                case R.id.mycat_Rl_myorder:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), "我的订单", Toast.LENGTH_LONG).show();

                        Intent intent=new Intent(getActivity(), MyCatAboutOrder.class);
                        startActivity(intent);
                    }
                    break;
                case R.id.mycat_Rl_taobaoorder:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), "淘宝订单", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.mycat_Rl_coupon:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), "我的优惠券", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.mycat_Rl_mine:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), "我的收藏", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.mycat_Rl_service:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), "联系客服", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.mycat_Rl_feedback:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        Toast.makeText(getContext(), "意见反馈", Toast.LENGTH_LONG).show();
                    }
                    break;
            }

            return true;
        }
    }

    private void aboutMycatRadiogroup() {
        mycat_radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.mycat_radiobutton_pay:
//                        Toast.makeText(getContext(), "代付款", Toast.LENGTH_LONG).show();
//
//                        break;
//                    case R.id.mycat_radiobutton_logistics:
//                        Toast.makeText(getContext(), "待发货", Toast.LENGTH_LONG).show();
//
//                        break;
//                    case R.id.mycat_radiobutton_delivery:
//                        Toast.makeText(getContext(), "待收货", Toast.LENGTH_LONG).show();
//
//                        break;
//                    case R.id.mycat_radiobutton_evaluate:
//                        Toast.makeText(getContext(), "待评价", Toast.LENGTH_LONG).show();
//
//                        break;
//                }
                Intent intent=new Intent(getActivity(), MyCatAboutOrder.class);
                startActivity(intent);

            }
        });
    }


    private void aboutRegiste() {

    }

    private void aboutLogin() {

        mycat_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}
