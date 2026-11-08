package com.mindflow.clarity.data

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mindflow.clarity.widget.ClarityQuoteWidgetProvider
import com.mindflow.clarity.widget.ClarityWidgetProvider
import java.util.UUID

enum class AppTheme(val id: Int, val label: String) {
    SYSTEM(0, "System Default"),
    LIGHT(1, "Light Mode"),
    DARK(2, "Dark Mode")
}

class StreakPreferences(private val context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val PREFS_NAME = "clarity_streak_prefs"
        private const val KEY_START_TIME = "key_start_time"
        private const val KEY_LONGEST_STREAK_MS = "key_longest_streak_ms"
        private const val KEY_TOTAL_RESETS = "key_total_resets"
        private const val KEY_HISTORY = "key_history"
        private const val KEY_THEME_MODE = "key_theme_mode"
    }

    init {
        // Initialize with current time if brand new install
        if (!prefs.contains(KEY_START_TIME)) {
            val now = System.currentTimeMillis()
            prefs.edit().putLong(KEY_START_TIME, now).apply()
        }
    }

    var themeMode: AppTheme
        get() {
            val id = prefs.getInt(KEY_THEME_MODE, AppTheme.SYSTEM.id)
            return AppTheme.values().firstOrNull { it.id == id } ?: AppTheme.SYSTEM
        }
        set(value) {
            prefs.edit().putInt(KEY_THEME_MODE, value.id).apply()
        }

    fun toggleTheme(): AppTheme {
        val next = when (themeMode) {
            AppTheme.DARK -> AppTheme.LIGHT
            AppTheme.LIGHT -> AppTheme.DARK
            AppTheme.SYSTEM -> AppTheme.LIGHT
        }
        themeMode = next
        return next
    }

    var startTimeMillis: Long
        get() = prefs.getLong(KEY_START_TIME, System.currentTimeMillis())
        set(value) {
            prefs.edit().putLong(KEY_START_TIME, value).apply()
            updateLongestStreakIfNeeded()
            updateAllWidgets()
        }

    var longestStreakMillis: Long
        get() = prefs.getLong(KEY_LONGEST_STREAK_MS, 0L)
        private set(value) {
            prefs.edit().putLong(KEY_LONGEST_STREAK_MS, value).apply()
        }

    var totalResets: Int
        get() = prefs.getInt(KEY_TOTAL_RESETS, 0)
        private set(value) {
            prefs.edit().putInt(KEY_TOTAL_RESETS, value).apply()
        }

    fun getTimeBreakdown(): TimeBreakdown {
        val now = System.currentTimeMillis()
        val start = startTimeMillis
        val diffMs = (now - start).coerceAtLeast(0L)
        val totalSecs = diffMs / 1000

        val days = totalSecs / (24 * 3600)
        val hours = (totalSecs % (24 * 3600)) / 3600
        val minutes = (totalSecs % 3600) / 60
        val seconds = totalSecs % 60

        return TimeBreakdown(
            totalSeconds = totalSecs,
            days = days,
            hours = hours,
            minutes = minutes,
            seconds = seconds
        )
    }

    fun getStreakDays(): Int {
        val now = System.currentTimeMillis()
        val diffMs = (now - startTimeMillis).coerceAtLeast(0L)
        return (diffMs / (1000L * 60 * 60 * 24)).toInt()
    }

    fun getLongestStreakDays(): Int {
        val currentDuration = System.currentTimeMillis() - startTimeMillis
        val longestMs = longestStreakMillis.coerceAtLeast(currentDuration)
        return (longestMs / (1000L * 60 * 60 * 24)).toInt()
    }

    fun resetStreak(triggerCategory: String, reflectionNote: String) {
        val now = System.currentTimeMillis()
        val currentStart = startTimeMillis
        val durationMs = (now - currentStart).coerceAtLeast(0L)
        val days = (durationMs / (1000L * 60 * 60 * 24)).toInt()

        if (durationMs > longestStreakMillis) {
            longestStreakMillis = durationMs
        }

        val record = StreakRecord(
            id = UUID.randomUUID().toString(),
            startTimestamp = currentStart,
            endTimestamp = now,
            daysCount = days,
            triggerCategory = triggerCategory.ifBlank { "Unspecified Trigger" },
            reflectionNote = reflectionNote.ifBlank { "Continuing the climb forward." }
        )

        val history = getHistory().toMutableList()
        history.add(0, record)
        saveHistory(history)

        totalResets += 1
        startTimeMillis = now
        updateAllWidgets()
    }

    fun updateLongestStreakIfNeeded() {
        val currentDuration = System.currentTimeMillis() - startTimeMillis
        if (currentDuration > longestStreakMillis) {
            longestStreakMillis = currentDuration
        }
    }

    fun getHistory(): List<StreakRecord> {
        val json = prefs.getString(KEY_HISTORY, null) ?: return emptyList()
        return try {
            val type = object : TypeToken<List<StreakRecord>>() {}.type
            gson.fromJson(json, type) ?: emptyList()
        } catch (e: Exception) {
            emptyList()
        }
    }

    private fun saveHistory(history: List<StreakRecord>) {
        val json = gson.toJson(history)
        prefs.edit().putString(KEY_HISTORY, json).apply()
    }

    fun updateAllWidgets() {
        try {
            val appWidgetManager = AppWidgetManager.getInstance(context)

            // Direct update for Counter Widgets
            val counterComponent = ComponentName(context, ClarityWidgetProvider::class.java)
            val counterIds = appWidgetManager.getAppWidgetIds(counterComponent)
            for (id in counterIds) {
                ClarityWidgetProvider.updateAppWidget(context, appWidgetManager, id)
            }

            // Direct update for Quote Widgets
            val quoteComponent = ComponentName(context, ClarityQuoteWidgetProvider::class.java)
            val quoteIds = appWidgetManager.getAppWidgetIds(quoteComponent)
            for (id in quoteIds) {
                ClarityQuoteWidgetProvider.updateAppWidget(context, appWidgetManager, id)
            }

            // Broadcast refresh as secondary assurance
            val counterIntent = Intent(context, ClarityWidgetProvider::class.java).apply {
                action = ClarityWidgetProvider.ACTION_WIDGET_REFRESH
            }
            context.sendBroadcast(counterIntent)

            val quoteIntent = Intent(context, ClarityQuoteWidgetProvider::class.java).apply {
                action = ClarityQuoteWidgetProvider.ACTION_WIDGET_REFRESH
            }
            context.sendBroadcast(quoteIntent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
