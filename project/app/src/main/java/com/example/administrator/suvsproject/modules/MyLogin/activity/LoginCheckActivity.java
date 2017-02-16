package com.example.administrator.suvsproject.modules.MyLogin.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.activity.BaseActivity;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.activity.Resiger.ResigerActivity;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Logindao;
import com.se7en.utils.SystemUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoginCheckActivity extends BaseActivity {

    private static final int LOGINCHRCK_RESULT_CODE = 1;
    private EditText editname;
    private EditText editpassword;
    private List<Logininfo> loginlist;

    @Override
    protected int setLatoutId() {
        return R.layout.activity_login_check;
    }
    @Override
    protected void findViews() {
        editname = (EditText) findViewById(R.id.login_username);
        editpassword = (EditText) findViewById(R.id.login_password);
    }

    @Override
    protected void init() {
        loginlist = new ArrayList<>();
    }
    @Override
    protected void initEvent() {
        findViewById(R.id.login_clean).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.setaccount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginCheckActivity.this, ResigerActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void loadData() {

    }
    @Override
    protected void requestNetData() {
        findViewById(R.id.login_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username= String.valueOf(editname.getText());
                String password= String.valueOf(editpassword.getText());

                Log.e("print", "onClick: "+username+">>>>>>>"+password );
                if(!TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    Logindao.request(username, password, new BaseCallBack() {
                        @Override
                        public void success(Object data) {
                            List<Logininfo> list= (List<Logininfo>) data;
                            loginlist.addAll(list);
                            Log.e("print", "onClick: "+list.get(0).getUsername()+">>>>>>>"+list.get(0).getBbsinfo() +list.get(0).getEmail());
                            setData();
                        }

                        @Override
                        public void failed(int errorCode, Object data) {
                            Toast.makeText(LoginCheckActivity.this, "网络错误"+errorCode, Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(LoginCheckActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setData() {
        if(loginlist.get(0).getErrcode().equals("0")){
            Intent intent =new Intent();
            try {
                SystemUtil.setSharedSerializable("loginfo",(Serializable)loginlist.get(0));
            } catch (IOException e) {
                e.printStackTrace();
            }
            SystemUtil.setSharedBoolean("firstLogin",true);
            intent.putExtra("loginfo", (Serializable) loginlist.get(0));
            LoginCheckActivity.this.setResult(LOGINCHRCK_RESULT_CODE,intent);
            finish();
        }else if(loginlist.get(0).getErrcode().equals("1")){
            Toast.makeText(LoginCheckActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }

    }
}
