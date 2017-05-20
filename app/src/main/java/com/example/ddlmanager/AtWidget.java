package com.example.ddlmanager;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class AtWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.at_widget);
       // views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Intent clickInt = new Intent(context, LoginActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, clickInt, 0);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item);
        remoteViews.setOnClickPendingIntent(R.id.llayout, pi);
        appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
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
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.item);
        Bundle bundle = intent.getExtras();
        if (intent.getAction().equals(STATICACTION)) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            remoteViews.setTextViewText(R.id.std, bundle.getString("std"));
            remoteViews.setTextViewText(R.id.edd, bundle.getString("edd"));
            remoteViews.setTextViewText(R.id.detail, bundle.getString("detail"));
            remoteViews.setTextViewText(R.id.percent, bundle.getString("percent"));
           // remoteViews.setProgressBar(R.id.seekBar,100,bundle.getInt("per"),false);
            //remoteViews.
            ComponentName componentName = new ComponentName(context,AtWidget.class);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }
    final String STATICACTION = "com.example.ddlmanager.staticreceiver";

}

