package com.lingshimall.lingshixiaomiao.fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by quanquan on 2016/7/9.
 */

    public class BlogsListView extends ListView {
        private GestureDetector mGestureDetector;
        View.OnTouchListener mGestureListener;

        public BlogsListView(Context context) {
            super(context);
        }

        public BlogsListView(Context context, AttributeSet attrs) {

            super(context, attrs);
            mGestureDetector = new GestureDetector(new YScrollDetector());
            setFadingEdgeLength(0);
        }

        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            return super.onInterceptTouchEvent(ev)
                    && mGestureDetector.onTouchEvent(ev);
        }

        class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                    float distanceX, float distanceY) {
                if (distanceY != 0 && distanceX != 0) {

                }
                if (Math.abs(distanceY) >= Math.abs(distanceX)) {
                    return true;
                }
                return false;
            }
        }

    }

