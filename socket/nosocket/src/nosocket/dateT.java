package nosocket;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dateT {
	public static void main(String[] args) {
		int p=10;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String    str    =    formatter.format(curDate);

        try {
            Date start = df.parse("2016-12-12");
            Date end = df.parse("2016-12-16");
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
        

        System.out.println(p);
	}
	
	
}
