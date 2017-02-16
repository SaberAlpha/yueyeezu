package com.example.administrator.suvsproject.modules.luntan.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseFragment;
import com.example.administrator.suvsproject.modules.luntan.adapter.MyFragmentAdapter;

/**
 * Created by Administrator on 2016/11/12 0012.
 */
public class QuanBuFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private RadioButton diquRadioButton, chexingRadioButton, zhutiRadioButton, jiaoyiRadioButton;
    private ViewPager viewPager;
    private Fragment[] fragments;
    private View view;

    @Override
    protected void findViews(View view) {
        this.view = view;
        radioGroup = (RadioGroup) view.findViewById(R.id.quanbu_rg);
        diquRadioButton = (RadioButton) view.findViewById(R.id.diqu_rb);
        chexingRadioButton = (RadioButton) view.findViewById(R.id.chexing_rb);
        zhutiRadioButton = (RadioButton) view.findViewById(R.id.zhuti_rb);
        jiaoyiRadioButton = (RadioButton) view.findViewById(R.id.jiaoyi_rb);
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
    }

    @Override
    protected void initEvent() {
        radioGroup.setOnCheckedChangeListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //更改RadioButton选中状态
                switch (position) {
                    case 0:
                        diquRadioButton.setChecked(true);
                        break;
                    case 1:
                        chexingRadioButton.setChecked(true);
                        break;
                    case 2:
                        zhutiRadioButton.setChecked(true);
                        break;
                    case 3:
                        jiaoyiRadioButton.setChecked(true);
                        break;
                }
//                if (radioGroup.getChildAt(position) instanceof RadioButton) {
//                    RadioButton checkedButton = (RadioButton) radioGroup.getChildAt(position);
//                    checkedButton.setChecked(true);
//                }
            }
        });
    }

    @Override
    protected void init() {
        fragments = new Fragment[]{new BankuaiDiQuFragment(), new BankuaiCheXingFragment(), new BankuaiZhuTiFragment(), new BankuaiJiaoYiFragment()};
        MyFragmentAdapter adapter = new MyFragmentAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_luntan_quanbu;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        RadioButton btn = (RadioButton) view.findViewById(checkedId);
        //根据tag属性获得当前应该设置选中的page的位置
        int position = Integer.parseInt(btn.getTag().toString());
        viewPager.setCurrentItem(position, false);
    }
}
