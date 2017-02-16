package com.example.administrator.suvsproject.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.cheku.activity.chekuFragment;
import com.example.administrator.suvsproject.modules.luntan.activity.LunTanFragment;
import com.example.administrator.suvsproject.modules.shipin.activity.Shipinvideo;
import com.example.administrator.suvsproject.modules.shop.activity.shopFragment;
import com.example.administrator.suvsproject.modules.shouye.activity.shouyeFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup radioGroup;
    private Fragment shouyefragmet,luntanfragmet,chekufragment,shipinfragment,shopfragment;

    private  int lastSelector=-1;

    Fragment[] fragments = new Fragment[5];
    @Override
    protected void findViews() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);

        shouyefragmet = new shouyeFragment();
        luntanfragmet=new LunTanFragment();
        chekufragment=new chekuFragment();
        shipinfragment=new Shipinvideo();
        shopfragment=new shopFragment();
        radioGroup.setOnCheckedChangeListener(this);
        addFragment(0);
    }




    @Override
    protected void init() {


    }

    @Override
    protected void initEvent() {

    }
    @Override
    protected void loadData() {

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void requestNetData() {

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {


        int position = 0;
        switch (checkedId){
            case R.id.radiobutton_shouye:
                position=0;
                break;
            case R.id.radiobutton_luntan:
                position=1;
                break;
            case R.id.radiobutton_cheku:
                position=2;
                break;
            case R.id.radiobutton_shipin:
                position=3;
                break;
            case R.id.radiobutton_shop:
                position=4;
                break;

        }
        addFragment(position);

    }
    private void addFragment(int position) {
        if(lastSelector==position){
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(lastSelector!=-1){
            transaction.hide(fragments[lastSelector]);
        }
        if (fragments[position] == null) {
            switch (position) {
                case 0:
                    fragments[0] = shouyefragmet;
                    break;
                case 1:
                    fragments[1] = luntanfragmet;
                    break;
                case 2:
                    fragments[2] = chekufragment;
                    break;
                case 3:
                    fragments[3] = shipinfragment;
                    break;
                case 4:
                    fragments[4] = shopfragment;
                    break;
            }
            transaction.add(R.id.container, fragments[position]);


    }else {
            transaction.show(fragments[position]);
        }
        transaction.commit();
        lastSelector=position;
    }
}
