package com.qrcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.suvsproject.R;
import com.example.administrator.suvsproject.modules.MyLogin.bean.Person.Logininfo;
import com.google.zxing.WriterException;
import com.se7en.utils.SystemUtil;
import com.zxing.encoding.EncodingHandler;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/17 0017.
 */
public class ErweiMaActivity extends Activity {
    private TextView resultTextView;
    private EditText qrStrEditText;
    private ImageView qrImgImageView;
    private String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_erweima);

//        resultTextView = (TextView) this.findViewById(R.id.tv_scan_result);
//        qrStrEditText = (EditText) this.findViewById(R.id.et_qr_string);
        qrImgImageView = (ImageView) this.findViewById(R.id.iv_qr_image);

        try {
            Logininfo loginfo = (Logininfo) SystemUtil.getSharedSerializable("loginfo");
            username = loginfo.getUsername();
            ((TextView)findViewById(R.id.myerweima_username)).setText(username);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        Button scanBarCodeButton = (Button) this.findViewById(R.id.btn_scan_barcode);
//        scanBarCodeButton.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//
//				Intent openCameraIntent = new Intent(MainActivity.this,CaptureActivity.class);
//				startActivityForResult(openCameraIntent, 0);
//			}
//		});

        findViewById(R.id.Myerweima_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button generateQRCodeButton = (Button) this.findViewById(R.id.btn_add_qrcode);
        generateQRCodeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    String contentString = username;
                    if (!contentString.equals("")) {

                        Bitmap qrCodeBitmap = EncodingHandler.createQRCode(contentString, 350,ErweiMaActivity.this);
                        qrImgImageView.setImageBitmap(qrCodeBitmap);
                    }else {
                        Toast.makeText(ErweiMaActivity.this, "Text can not be empty", Toast.LENGTH_SHORT).show();
                    }

                } catch (WriterException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            resultTextView.setText(scanResult);
        }
    }
}
