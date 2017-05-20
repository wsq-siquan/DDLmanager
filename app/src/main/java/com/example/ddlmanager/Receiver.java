package com.example.ddlmanager;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Created by hunan on 16-12-17.
 */
public class Receiver extends BroadcastReceiver {
    final String STATICACTION = "com.example.ddlmanager.staticreceiver";
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(STATICACTION)) {
//            Bundle bundle = intent.getExtras();
//            Notification.Builder builder = new Notification.Builder(context);
//
//            Intent notificationIntent = new Intent(context, MainActivity.class);
//            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
//
//
//
//            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//            Notification notification = builder.build();
//            manager.notify(0, notification);


            Bundle bundle = intent.getExtras();
            String name = bundle.getString("detail");

            String ddl = "DDL为 "+bundle.getString("edd");
            //int src = bundle.getInt("src");
            //Toast.makeText(context,name,Toast.LENGTH_SHORT).show();

            NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);


            Notification.Builder builder = new Notification.Builder(context);
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(),R.mipmap.dmlogo);
            Intent notificationIntent =new Intent(context, MainActivity.class); // 点击该通知后要跳转的Activity
            PendingIntent contentItent = PendingIntent.getActivity(context, 0, notificationIntent, 0);


            builder.setContentTitle(name).setContentText(ddl).setTicker("您最近的DDL").
                    setLargeIcon(bm).setSmallIcon(R.mipmap.dmlogo).setAutoCancel(true).setContentIntent(contentItent);

            Notification notification = builder.build();

            nm.notify(0,notification);


        }
    }
}
