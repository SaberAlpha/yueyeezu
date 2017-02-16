package com.example.administrator.suvsproject.modules.MyLogin.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class MyHomeScrollView extends ScrollView{
    public MyHomeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //重写ScrollView
    @Override
    protected int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        return 0;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
