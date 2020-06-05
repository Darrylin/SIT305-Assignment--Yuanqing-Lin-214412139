package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolUtils {

    public static String getCurDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        return simpleDateFormat.format(new Date());
    }

    public static String getCurDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

}
