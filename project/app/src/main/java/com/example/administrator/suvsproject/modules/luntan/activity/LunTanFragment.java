package com.example.administrator.suvsproject.modules.luntan.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.activity.LogActivity;

/**
 * Created by Administrator on 2016/11/11 0011.
 */
public class LunTanFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private ImageView mine;
    private ImageView search;
    private RadioGroup tabRadioGroup;
    private RadioButton jxRb;
    private RadioButton qbRb;
    private LinearLayout tabLayout;
    private View slide1;
    private View slide2;

    private int lastIndex = -1;
    private Fragment[] fragments = new Fragment[2];

    @Override
    protected void findViews(View view) {
        mine = (ImageView) view.findViewById(R.id.my_information_luntan);
        search = (ImageView) view.findViewById(R.id.my_search);
        tabRadioGroup = (RadioGroup) view.findViewById(R.id.tab_rg);
        jxRb = (RadioButton) view.findViewById(R.id.jingxuan_rb);
        qbRb = (RadioButton) view.findViewById(R.id.quanbu_rb);
        slide1 = view.findViewById(R.id.slide1);
        slide2 = view.findViewById(R.id.slide2);
    }

    @Override
    protected void initEvent() {
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LogActivity.class);
                startActivity(intent);
            }
        });

        tabRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    protected void init() {
        addFragment(0);
        slide1.setVisibility(View.VISIBLE);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_luntan;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int position = 0;
        switch (checkedId) {
            case R.id.jingxuan_rb:
                slide1.setVisibility(View.VISIBLE);
                slide2.setVisibility(View.INVISIBLE);
                position = 0;
                break;
            case R.id.quanbu_rb:
                slide1.setVisibility(View.INVISIBLE);
                slide2.setVisibility(View.VISIBLE);
                position = 1;
                break;
        }
        addFragment(position);
    }

    private void addFragment(int position) {
        if (lastIndex == position) {
            return;
        }
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        if (lastIndex != -1) {
            transaction.hide(fragments[lastIndex]);
        }

        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[0] = new JingXuanFragment();
                    break;
                case 1:
                    fragments[1] = new QuanBuFragment();
                    break;
            }
            transaction.add(R.id.fragment_container, fragments[position]);
        } else {
            transaction.show(fragments[position]);
        }
        lastIndex = position;
        transaction.commit();
    }
}
