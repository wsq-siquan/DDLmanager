package com.example.ddlmanager;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    public ComponentName thisWidget; //组件名
    public AppWidgetManager manager; // AppWidget管理器

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);
        //views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent clickInt = new Intent(context, MainActivity.class); //跳转到登陆界面
        PendingIntent pi = PendingIntent.getActivity(context,0,clickInt,0);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        //rv.setOnClickPendingIntent(R.id.Widget_image,pi);
        appWidgetManager.updateAppWidget(appWidgetIds, rv);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }


    @Override
    public  void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);
        RemoteViews rv = new RemoteViews(context.getPackageName(),R.layout.new_app_widget);
        Bundle bundle = intent.getExtras();
        thisWidget = new ComponentName(context,NewAppWidget.class);

        if(intent.getAction().equals("com.example.ddlmanager.staticreceiver")) {
            String detail = bundle.getString("detail");
            String std = bundle.getString("std");
            String edd = bundle.getString("edd");
            int per = bundle.getInt("per");
            String percnet = String.valueOf(per)+"%";
            //Toast.makeText(context,name+"\t"+src ,Toast.LENGTH_SHORT).show();
//            rv.setTextViewText(R.id.appwidget_text, name);
//            rv.setImageViewResource(R.id.Widget_image, src);

            rv.setProgressBar(R.id.progressbar_nw,100,per,false);
            rv.setTextViewText(R.id.detail_nw,detail);
            rv.setTextViewText(R.id.std_nw,std);
            rv.setTextViewText(R.id.edd_nw,edd);
            rv.setTextViewText(R.id.percent_nw,percnet);

            manager = AppWidgetManager.getInstance(context);
            manager.updateAppWidget(thisWidget , rv);

        }
    }

}

