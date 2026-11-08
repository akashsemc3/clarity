package com.mindflow.clarity.data

/**
 * Represents a milestone reached as clean days accumulate.
 * Discreet, scientific, empowering, and focused on neural rewiring and vitality.
 */
data class Milestone(
    val dayThreshold: Int,
    val title: String,
    val phase: String,
    val badge: String,
    val summary: String,
    val neurobiology: String,
    val physicalBenefit: String,
    val mindsetShift: String,
    val recommendedAction: String
)

/**
 * Historical record of a past streak and learnings.
 */
data class StreakRecord(
    val id: String,
    val startTimestamp: Long,
    val endTimestamp: Long,
    val daysCount: Int,
    val triggerCategory: String,
    val reflectionNote: String
)

/**
 * Curated motivational quote.
 */
data class WisdomQuote(
    val id: Int,
    val quote: String,
    val author: String,
    val tag: String // Stoicism, Neuroscience, Discipline, Focus
)

/**
 * Live time breakdown for live counter.
 */
data class TimeBreakdown(
    val totalSeconds: Long,
    val days: Long,
    val hours: Long,
    val minutes: Long,
    val seconds: Long
) {
    val formattedDuration: String
        get() = "${days}d ${hours}h ${minutes}m ${seconds}s"
}
