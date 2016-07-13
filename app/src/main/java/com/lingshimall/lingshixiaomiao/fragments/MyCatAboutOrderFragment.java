package com.lingshimall.lingshixiaomiao.fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lingshimall.lingshixiaomiao.MainActivity;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.activitys.MyCatAboutOrder;

/**
 * A placeholder fragment containing a simple view.
 */
public class MyCatAboutOrderFragment extends Fragment {

    private ImageView fragment_order_iv;

    private TextView fragment_order_tv1,fragment_order_tv2;

    private Button fragment_order_bt;


    private int index;

    public MyCatAboutOrderFragment() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.viewpager_fragment_my_cat_about_order,null);

        fragment_order_iv= (ImageView) view.findViewById(R.id.fragment_order_iv);
        fragment_order_tv1= (TextView) view.findViewById(R.id.fragment_order_tv1);
        fragment_order_tv2= (TextView) view.findViewById(R.id.fragment_order_tv2);
        fragment_order_bt= (Button) view.findViewById(R.id.fragment_order_bt);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fragment_order_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

    }
}
