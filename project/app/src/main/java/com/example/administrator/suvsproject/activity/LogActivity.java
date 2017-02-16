package com.example.administrator.suvsproject.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Caogao;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Collection.MyCollection;
import com.example.administrator.suvsproject.modules.MyLogin.activity.LoginCheckActivity;
import com.example.administrator.suvsproject.modules.MyLogin.activity.MyFriend;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Myhome.MyhomeActivity;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Search.SearchActivity;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Setting.SettingActivity;
import com.example.administrator.suvsproject.modules.MyLogin.activity.SiXin;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Tiezi.Mytiezi;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.se7en.utils.SystemUtil;
import com.zxing.activity.CaptureActivity;

import java.io.IOException;

public class LogActivity extends BaseActivity  {

    private static final int LOGIN_REQUEST_CODE = 0;
    private View backview;
    private String errcode="1";
    private View exit;

    @Override
    protected void findViews() {
        backview = findViewById(R.id.back_login);
        exit = findViewById(R.id.login_exit);

    }
    @Override
    protected void init() {
        if(SystemUtil.getSharedBoolean("firstLogin",false)){
            try {
                Logininfo  firstloginfo = (Logininfo) SystemUtil.getSharedSerializable("loginfo");
                errcode= firstloginfo.getErrcode();
                if(errcode.equals("0")){
                    ((TextView)findViewById(R.id.login_text)).setText(firstloginfo.getUsername());
                    ((ImageView)findViewById(R.id.login_icon)).setImageResource(R.drawable.personal_icon);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void initEvent() {
        backview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void loginrequest(View view){
        if(errcode.equals("1")){
            Intent intent=new Intent(LogActivity.this, LoginCheckActivity.class);
            startActivityForResult(intent,LOGIN_REQUEST_CODE);
        }else if(errcode.equals("0")){
            Intent intent1=new Intent(LogActivity.this, MyhomeActivity.class);
            startActivity(intent1);
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                Logininfo loginfo = (Logininfo) data.getSerializableExtra("loginfo");
                errcode = loginfo.getErrcode();
                exit.setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.login_text)).setText(loginfo.getUsername());
                ((ImageView)findViewById(R.id.login_icon)).setImageResource(R.drawable.personal_icon);
                break;
        }

    }

    @Override
    protected void loadData() {

    }

    public void loginClick(View view){
        switch (view.getId()){

            case R.id.login_search:
                Intent intent6=new Intent(this, SearchActivity.class);
                startActivity(intent6);
                return;
            case R.id.login_setting:
                Intent intent7=new Intent(this, SettingActivity.class);
                startActivity(intent7);
                return;
            case R.id.login_exit:
                if(errcode.equals("0")){
                    try {
                        Logininfo  firstloginfo = (Logininfo) SystemUtil.getSharedSerializable("loginfo");
                        firstloginfo.setErrcode("1");
                        SystemUtil.setSharedSerializable("loginfo",firstloginfo);
                        SystemUtil.setSharedBoolean("firstLogin",false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    ((TextView)findViewById(R.id.login_text)).setText("立即登录");
                    ((ImageView)findViewById(R.id.login_icon)).setImageResource(R.drawable.user_default);
                    errcode="1";
                    exit.setVisibility(View.INVISIBLE);
                    return;
                }

        }

        if(errcode.equals("1")){
            Intent loginIntent=new Intent(LogActivity.this,LoginCheckActivity.class);
            startActivityForResult(loginIntent,LOGIN_REQUEST_CODE);
        }else
        switch (view.getId()){
            case R.id.login_save:
                Intent intent=new Intent(this, MyCollection.class);
                startActivity(intent);
                break;
            case R.id.login_tiezi:
                Intent intent1=new Intent(this, Mytiezi.class);
                startActivity(intent1);
                break;
            case R.id.login_friend:
                Intent intent2=new Intent(this, MyFriend.class);
                startActivity(intent2);
                break;
            case R.id.login_saoyisao:
                Intent intent3=new Intent(this,CaptureActivity.class);
                startActivity(intent3);
                break;
            case R.id.login_shixin:
                Intent intent4=new Intent(this, SiXin.class);
                startActivity(intent4);
                break;
            case R.id.login_caogao:
                Intent intent5=new Intent(this, Caogao.class);
                startActivity(intent5);
                break;


        }
    }




    @Override
    protected int setLatoutId() {
        return R.layout.activity_log;
    }

    @Override
    protected void requestNetData() {

    }


}
