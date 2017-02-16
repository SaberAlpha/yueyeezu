package com.example.administrator.suvsproject.modules.cheku.util;

import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class Utils {

    public static String getTime(String times) {
        long time = Long.parseLong(times);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(time);
    }

}
