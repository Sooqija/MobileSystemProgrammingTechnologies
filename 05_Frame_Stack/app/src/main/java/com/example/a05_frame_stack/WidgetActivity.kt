package com.example.a05_frame_stack

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.Toast

class WidgetActivity: AppWidgetProvider() {

    val ACTION_CHANGE = "change_count"

    override fun onAppWidgetOptionsChanged(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetId: Int,
        newOptions: Bundle?
    ) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions)
    }


    @SuppressLint("RemoteViewLayout")
    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action.equals(ACTION_CHANGE)) {
            val views = RemoteViews(context?.packageName, R.layout.activity_widget)
            count++
            views.setTextViewText(R.id.Score, count.toString())
            val appWidget = ComponentName (context!!, WidgetActivity::class.java!!)
            val appWidgetManager = AppWidgetManager.getInstance(context)

            appWidgetManager.updateAppWidget(appWidget, views)
        }
    }

    @SuppressLint("RemoteViewLayout")
    override fun onUpdate(
        context: Context?,
        appWidgetManager: AppWidgetManager?,
        appWidgetIds: IntArray?
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        val intent = Intent(context, WidgetActivity::class.java)
        intent.setAction(ACTION_CHANGE)

        val pending = PendingIntent.getBroadcast(context, 0, intent, 0)
        val views = RemoteViews(context?.packageName, R.layout.activity_widget)

        views.setOnClickPendingIntent(R.id.Increase, pending)
        appWidgetManager?.updateAppWidget(appWidgetIds, views)
        count = 0
        Toast.makeText(context, "widget added", Toast.LENGTH_SHORT).show()
    }
}