package com.example.administrator.suvsproject.modules.cheku.net;

import android.os.AsyncTask;

import com.example.administrator.suvsproject.modules.cheku.bean.CarBean;
import com.example.administrator.suvsproject.modules.cheku.util.ChekuJsonParseUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/11/14 0014.
 */
public class MyTask extends AsyncTask<String, Void, List<CarBean>> {

    CallBack cb;

    public MyTask(CallBack cb) {
        super();
        this.cb = cb;
    }

    public interface CallBack {
        void getCarList(List<CarBean> data);
    }


    @Override
    protected List<CarBean> doInBackground(String... params) {
        HttpURLConnection con = null;
        try {
            URL url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();
            InputStream is = con.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));

            String flag;
            StringBuilder sb = new StringBuilder();
            while ((flag = reader.readLine()) != null) {
                sb.append(flag);
            }
            reader.close();
            List<CarBean> carList = ChekuJsonParseUtil.getCarBeenListofDeault(sb.toString());
            return carList;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }


    @Override
    protected void onPostExecute(List<CarBean> carBeen) {
        super.onPostExecute(carBeen);
        if (cb != null) {
            cb.getCarList(carBeen);
        }
    }
}

