package com.example.administrator.suvsproject.modules.cheku.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.suvsproject.R;

/**
 * Created by Administrator on 2016/11/15 0015.
 */
public class ChekuPicsFragment extends Fragment {

    View view;
    ChekuPicsAppFragment apprence;
    ChekuPicsInteriorFragment interior;
    ChekuPicsDetailsFragment details;
    FragmentManager fm;

    FrameLayout cheku_pics_fl;
    RadioGroup cheku_pics_rg;
    RadioButton cheku_pics_rg_app, cheku_pics_rg_interior, cheku_pics_rg_details;

    String words;
    //记录上次哪个fragment显示
    int location = 0;

    Bundle b;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cheku_pics, null);
        words = getArguments().getString("words");
        b = new Bundle();
        b.putString("words", words);

        initView();
        init();
        initEvent();

        return view;
    }

    private void initView() {
        cheku_pics_rg = (RadioGroup) view.findViewById(R.id.cheku_pics_rg);
        cheku_pics_rg_app = (RadioButton) view.findViewById(R.id.cheku_pics_rg_app);
        cheku_pics_rg_details = (RadioButton) view.findViewById(R.id.cheku_pics_rg_details);
        cheku_pics_rg_interior = (RadioButton) view.findViewById(R.id.cheku_pics_rg_interior);
        cheku_pics_fl = (FrameLayout) view.findViewById(R.id.cheku_pics_fl);
    }

    private void init() {
        fm = getChildFragmentManager();
        apprence = new ChekuPicsAppFragment();
        apprence.setArguments(b);
        fm.beginTransaction().add(R.id.cheku_pics_fl, apprence).commit();
    }


    private void initEvent() {
        cheku_pics_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.cheku_pics_rg_app:

                        if (location == 1) {
                            fm.beginTransaction().hide(interior).show(apprence).commit();
                        }

                        if (location == 2) {
                            fm.beginTransaction().hide(details).show(apprence).commit();
                        }
                        location = 0;
                        break;

                    case R.id.cheku_pics_rg_interior:
                        if (interior == null) {
                            interior = new ChekuPicsInteriorFragment();
                            interior.setArguments(b);
                            fm.beginTransaction().add(R.id.cheku_pics_fl, interior).commit();
                        }
                        if (location == 0) {
                            fm.beginTransaction().hide(apprence).show(interior).commit();
                        }
                        if (location == 2) {
                            fm.beginTransaction().hide(details).show(interior).commit();
                        }
                        location = 1;
                        break;

                    case R.id.cheku_pics_rg_details:
                        if (details == null) {
                            details = new ChekuPicsDetailsFragment();
                            details.setArguments(b);
                            fm.beginTransaction().add(R.id.cheku_pics_fl, details).commit();
                        }
                        if (location == 0) {
                            fm.beginTransaction().hide(apprence).show(details).commit();
                        }
                        if (location == 1) {
                            fm.beginTransaction().hide(interior).show(details).commit();
                        }
                        location = 2;
                        break;
                }
            }
        });
    }

}
