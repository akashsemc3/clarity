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
import com.mindflow.clarity.data.StreakPreferences

class ClarityWidgetProvider : AppWidgetProvider() {

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
            val thisWidget = ComponentName(context, ClarityWidgetProvider::class.java)
            val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
            for (widgetId in allWidgetIds) {
                updateAppWidget(context, appWidgetManager, widgetId)
            }
        }
    }

    companion object {
        const val ACTION_WIDGET_REFRESH = "com.mindflow.clarity.ACTION_REFRESH_COUNTER_WIDGET"

        fun updateAppWidget(
            context: Context,
            appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            try {
                val prefs = StreakPreferences(context)
                val days = prefs.getStreakDays()
                val breakdown = prefs.getTimeBreakdown()
                val nextMilestone = BenefitsRepository.getNextMilestone(days)
                val progress = (BenefitsRepository.getProgressToNextMilestone(days) * 100).toInt()
                val rank = BenefitsRepository.getRankName(days).uppercase()

                val views = RemoteViews(context.packageName, R.layout.widget_clarity_counter)

                views.setTextViewText(R.id.widget_app_title, "CLARITY")
                views.setTextViewText(R.id.widget_rank_label, rank)
                views.setTextViewText(R.id.widget_streak_days, "DAY $days")
                views.setTextViewText(
                    R.id.widget_streak_time,
                    "${breakdown.hours}h ${breakdown.minutes}m clean"
                )

                views.setProgressBar(R.id.widget_progress, 100, progress, false)

                if (nextMilestone != null) {
                    val daysLeft = nextMilestone.dayThreshold - days
                    views.setTextViewText(
                        R.id.widget_milestone_target,
                        "Next: ${nextMilestone.title} (in ${daysLeft}d)"
                    )
                } else {
                    views.setTextViewText(R.id.widget_milestone_target, "Supreme Mastery Achieved")
                }

                // Click pending intent to launch MainActivity
                val launchIntent = Intent(context, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                val pendingIntent = PendingIntent.getActivity(
                    context,
                    appWidgetId,
                    launchIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
                views.setOnClickPendingIntent(R.id.widget_counter_root, pendingIntent)

                appWidgetManager.updateAppWidget(appWidgetId, views)
            } catch (e: Exception) {
                // Safeguard against inflation issues
                e.printStackTrace()
            }
        }
    }
}
