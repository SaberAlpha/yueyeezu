package com.example.administrator.suvsproject.modules.MyLogin.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ScrollHomeViewPager extends ViewPager {
    public ScrollHomeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int[] heights = new int[getChildCount()];
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
            heights[i] = child.getMeasuredHeight();
        }
        Arrays.sort(heights);
        setMeasuredDimension(getMeasuredWidth(), heights[heights.length-1]);
        // int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
        // MeasureSpec.AT_MOST);
        // super.onMeasure(widthMeasureSpec, expandSpec);
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @param view        the base view with already measured height
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureHeight(int measureSpec, View view, View view1) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            // set the height from the base view if available
            if (view != null && view1 != null) {
                result = view.getMeasuredHeight() > view1.getMeasuredHeight() ? view
                        .getMeasuredHeight() : view1.getMeasuredHeight();
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
