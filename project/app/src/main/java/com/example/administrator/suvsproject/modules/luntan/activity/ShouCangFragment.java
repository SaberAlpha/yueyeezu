package com.example.administrator.suvsproject.modules.luntan.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class ShouCangFragment extends BaseFragment{
    private String title;
    private LinearLayout infoContainer;
    private TextView noInfo;
    private TextView textView;
    private String beforeTitle = null;

    @Override
    protected void findViews(View view) {
        infoContainer = (LinearLayout) view.findViewById(R.id.luntan_info_layout);
        noInfo = (TextView) view.findViewById(R.id.no_shoucang_tv);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_luntan_info_shoucang;
    }

    @Subscribe
    public void getShouCang(String title){
        if (!title.equals("1") && !title.equals(beforeTitle)) {
            noInfo.setVisibility(View.GONE);
            this.title = title;
            textView = new TextView(getContext());
            textView.setText(title);

            LinearLayout.LayoutParams  layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT );
            layoutParams.leftMargin = 5;
            layoutParams.rightMargin = 5;
            textView.setLayoutParams(layoutParams);
            textView.setBackgroundResource(R.drawable.shoucang_info_shape);
            infoContainer.addView(textView);
            beforeTitle = title;
        }else{
            noInfo.setVisibility(View.VISIBLE);
        }
    }
}
