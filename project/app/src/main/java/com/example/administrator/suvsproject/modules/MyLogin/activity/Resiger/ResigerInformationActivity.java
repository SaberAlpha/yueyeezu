package com.example.administrator.suvsproject.modules.MyLogin.activity.Resiger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.i.BaseCallBack;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.example.administrator.suvsproject.modules.MyLogin.dao.Person.Registerdao;
import com.se7en.utils.SystemUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ResigerInformationActivity extends AppCompatActivity {

    private static final int LOGINCHRCK_RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resiger_information);

        String telenum = getIntent().getStringExtra("telenum");
        String keycode = getIntent().getStringExtra("keycode");
        String  usernametext= String.valueOf(((EditText) findViewById(R.id.registerimformation_username)).getText());
        String emailtext= String.valueOf(((EditText) findViewById(R.id.registerimformation_email)).getText());
        String passwordtext= String.valueOf(((EditText) findViewById(R.id.registerimformation_password)).getText());

        if(!TextUtils.isEmpty(usernametext)&&!TextUtils.isEmpty(emailtext)&&!TextUtils.isEmpty(passwordtext)){
            Registerdao.request(3, telenum, keycode, passwordtext, usernametext, emailtext, new BaseCallBack() {
                @Override
                public void success(Object data) {
                    List<Logininfo> loginlist= (List<Logininfo>) data;
                    Intent intent =new Intent();
                    try {
                        SystemUtil.setSharedSerializable("loginfo",(Serializable)loginlist.get(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SystemUtil.setSharedBoolean("firstLogin",true);
                    intent.putExtra("loginfo", (Serializable) loginlist.get(0));
                   ResigerInformationActivity.this.setResult(LOGINCHRCK_RESULT_CODE,intent);
                    finish();
                }

                @Override
                public void failed(int errorCode, Object data) {
                    List<Logininfo> loginlist= (List<Logininfo>) data;
                    Toast.makeText(ResigerInformationActivity.this, loginlist.get(0).getBbsinfo(), Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(ResigerInformationActivity.this, "用户名或密码或邮箱为空,请重新输入", Toast.LENGTH_SHORT).show();
        }

    }
}
