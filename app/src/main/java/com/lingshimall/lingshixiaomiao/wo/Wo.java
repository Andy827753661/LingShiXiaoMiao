package com.lingshimall.lingshixiaomiao.wo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lingshimall.lingshixiaomiao.R;

/**
 * Created by 张 波 on 2016/7/6.
 */
public class Wo extends Fragment {

    private View view;

    public Wo() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstaneState) {
        view = inflater.inflate(R.layout.fragment_wo_layout, container, false);
        return view;
    }
}
