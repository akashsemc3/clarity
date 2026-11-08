package com.mindflow.clarity.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.mindflow.clarity.MainActivity
import com.mindflow.clarity.R
import com.mindflow.clarity.data.BenefitsRepository
import com.mindflow.clarity.data.QuotesRepository
import com.mindflow.clarity.data.StreakPreferences

class ClarityQuoteWidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_WIDGET_REFRESH) {
            val appWidgetManager = AppWidgetManager.getInstance(context)
            val thisWidget = ComponentName(context, ClarityQuoteWidgetProvider::class.java)
            val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
            for (widgetId in allWidgetIds) {
                updateAppWidget(context, appWidgetManager, widgetId)
            }
        }
    }

    companion object {
        const val ACTION_WIDGET_REFRESH = "com.mindflow.clarity.ACTION_REFRESH_QUOTE_WIDGET"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            try {
                val prefs = StreakPreferences(context)
                val days = prefs.getStreakDays()
                val currentMilestone = BenefitsRepository.getCurrentMilestone(days)
                val quote = QuotesRepository.getDailyQuote(days)

                val views = RemoteViews(context.packageName, R.layout.widget_clarity_quote)

                views.setTextViewText(R.id.widget_quote_streak_days, "DAY $days")
                views.setTextViewText(
                    R.id.widget_quote_milestone_title,
                    currentMilestone.title
                )
                views.setTextViewText(R.id.widget_quote_text, "\"${quote.quote}\"")
                views.setTextViewText(R.id.widget_quote_author, "— ${quote.author}")
                views.setImageViewResource(R.id.widget_btn_shield, R.drawable.ic_shield)

                // Click pending intent for the whole widget (opens Dashboard)
                val openIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                val openPendingIntent = PendingIntent.getActivity(
                    context,
                    appWidgetId * 10,
                    openIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widget_quote_root, openPendingIntent)

                // Click pending intent for Shield button (opens Shield screen directly)
                val shieldIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    putExtra(MainActivity.EXTRA_NAV_TARGET, MainActivity.NAV_SHIELD)
                }
                val shieldPendingIntent = PendingIntent.getActivity(
                    context,
                    appWidgetId * 10 + 1,
                    shieldIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widget_btn_shield, shieldPendingIntent)

                appWidgetManager.updateAppWidget(appWidgetId, views)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
