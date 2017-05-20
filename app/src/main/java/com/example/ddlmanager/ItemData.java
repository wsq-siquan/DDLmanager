package com.example.ddlmanager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 王思全 on 2016/12/14.
 */
public class ItemData {
    public String detail,std_s,edd_s;
    public int progress = 10;
    public Date startd,endd;
    public int p=0;
    ItemData(String d,String s,String e) throws ParseException {
        detail =d;
        std_s = s;
        edd_s = e;
        progress =calprog();
    }
    public int calprog() throws ParseException {
        p=0;

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);

        try {
            Date start = df.parse(std_s);
            Date end = df.parse(edd_s);
            Date now = df.parse(str);

            long diff = end.getTime() - start.getTime();//这样得到的差值是微秒级别

            long days = diff / (1000 * 60 * 60 * 24);
            double  intd = (double)days;

            long sdiff = now.getTime() - start.getTime();
            long sdays = sdiff / (1000 * 60 * 60 * 24);
            double sintd = (double)sdays;

            p  = (int)(sintd/intd*100);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return p;
    }


}
