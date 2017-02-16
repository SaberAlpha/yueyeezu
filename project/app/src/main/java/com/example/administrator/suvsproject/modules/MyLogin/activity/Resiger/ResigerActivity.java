package com.example.administrator.suvsproject.modules.MyLogin.activity.Resiger;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Registerdao;

import java.util.List;

public class ResigerActivity extends BaseActivity {


    private EditText telediter;
    private EditText keycode;
    private TextView text;
    private int time=36;

    @Override
    protected void findViews() {
        telediter = (EditText) findViewById(R.id.register_telnumber);
        keycode = (EditText) findViewById(R.id.register_keycode);
        text = (TextView) findViewById(R.id.getkeycode);

    }

    @Override
    protected void init() {

    }


    @Override
    protected void loadData() {

    }

    @Override
    protected void initEvent() {
        findViewById(R.id.register_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        findViewById(R.id.register_step1_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String telephone= String.valueOf(telediter.getText());
               final String keys= String.valueOf(keycode.getText());

                if(!TextUtils.isEmpty(telephone)&&!TextUtils.isEmpty(keys)){
                    Registerdao.request(2, telephone, keys, null, null, null, new BaseCallBack() {
                        @Override
                        public void success(Object data) {
                            List<Logininfo> list= (List<Logininfo>) data;
                            Toast.makeText(ResigerActivity.this, list.get(0).getBbsinfo(), Toast.LENGTH_SHORT).show();
                            Intent intent =new Intent(ResigerActivity.this,ResigerActivity.class);
                            intent.putExtra("telenum",telephone);
                            intent.putExtra("keycode",keys);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void failed(int errorCode, Object data) {
                            List<Logininfo> list= (List<Logininfo>) data;
                            Toast.makeText(ResigerActivity.this, list.get(0).getBbsinfo(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(ResigerActivity.this, "用户名或验证码为空,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(time==0){
                    text.setText("请获取/n验证码");
                    time=36;
                    return;
                }
                final String telephone= String.valueOf(telediter.getText());
                final String keys= String.valueOf(keycode.getText());

                if(!TextUtils.isEmpty(telephone)){
                    Registerdao.request(1, telephone, null, null, null, null, new BaseCallBack() {
                        @Override
                        public void success(Object data) {
                            List<Logininfo> list= (List<Logininfo>) data;
                            Toast.makeText(ResigerActivity.this, list.get(0).getBbsinfo(), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void failed(int errorCode, Object data) {
                            List<Logininfo> list= (List<Logininfo>) data;
                            Toast.makeText(ResigerActivity.this, list.get(0).getBbsinfo(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(ResigerActivity.this, "手机号码为空,请重新输入", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    Handler handler=new Handler();
    private void updateprogress() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(time==0){
                    return;
                }
                time--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(time+"s");
                    }
                });
               updateprogress();
            }
        },1000);
    }


    @Override
    protected void requestNetData() {

    }

    @Override
    protected int setLatoutId() {
        return R.layout.activity_resiger;
    }
}
