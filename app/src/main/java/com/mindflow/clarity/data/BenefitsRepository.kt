package com.mindflow.clarity.data

object BenefitsRepository {

    val milestones = listOf(
        Milestone(
            dayThreshold = 1,
            title = "The Clean Slate",
            phase = "Phase 1: Initiation",
            badge = "🌱 Day 1",
            summary = "The initial reset. Cravings begin to test your resolve, but dopamine receptors start their crucial rest cycle.",
            neurobiology = "Dopamine D2 receptor downregulation pauses. The ventral tegmental area (reward center) begins adjusting to the absence of extreme artificial stimulation.",
            physicalBenefit = "Restoration of natural circadian rhythm begins. Basal cortisol begins to normalize after the first 24 hours.",
            mindsetShift = "The journey of a thousand miles begins with a single deliberate choice. Acknowledge discomfort without reacting to it.",
            recommendedAction = "Drink 3 liters of water today. Avoid all screens 1 hour before sleep."
        ),
        Milestone(
            dayThreshold = 3,
            title = "Overcoming Inertia",
            phase = "Phase 1: Initiation",
            badge = "⚡ Day 3",
            summary = "The initial physical restlessness peaks and begins to subside. Mental fog begins to thin.",
            neurobiology = "Prefrontal cortex (PFC) executive control circuits are actively forming new inhibitory connections. The brain notices the break in habitual autopilot loops.",
            physicalBenefit = "Stabilization of morning energy levels. Reduced lethargy and fewer energy crashes in the afternoon.",
            mindsetShift = "Urges are like ocean waves: they rise to a crest, last a few minutes, and naturally collapse. Ride the wave; do not drown in it.",
            recommendedAction = "Take a 3-minute cold shower or do 30 pushups whenever restless energy strikes."
        ),
        Milestone(
            dayThreshold = 7,
            title = "The Vitality Surge",
            phase = "Phase 2: Momentum",
            badge = "🔥 Day 7",
            summary = "Documented natural spike in androgen receptor sensitivity and baseline vitality. A palpable surge in inner drive and clarity.",
            neurobiology = "Dopamine receptor sensitivity increases. Everyday tasks, music, and social conversations begin feeling naturally more engaging.",
            physicalBenefit = "Significantly heightened physical energy, faster gym recovery, crisper mental sharpness, and deeper sleep.",
            mindsetShift = "Energy cannot be destroyed—only transmuted. Direct this natural fire into your physical strength, career, or creative craft.",
            recommendedAction = "Channel your energy into heavy resistance training or an intensive 90-minute deep work block."
        ),
        Milestone(
            dayThreshold = 14,
            title = "Neural Equilibrium",
            phase = "Phase 2: Momentum",
            badge = "🛡️ Day 14",
            summary = "Two weeks of self-sovereignty. Social anxiety drops sharply. Eye contact becomes natural, effortless, and confident.",
            neurobiology = "Baseline delta-FosB (a protein linked to compulsive reinforcement) begins declining. Neural pathways for emotional regulation strengthen.",
            physicalBenefit = "Clearer skin, brighter eyes, improved vocal tone resonance, and steady mental stamina throughout the day.",
            mindsetShift = "You no longer feel like someone fighting an uphill battle. You are standing on solid ground with restored self-respect.",
            recommendedAction = "Practice holding calm, friendly eye contact in every conversation today. Notice how grounded you feel."
        ),
        Milestone(
            dayThreshold = 21,
            title = "The Habit Threshold",
            phase = "Phase 3: Rewiring",
            badge = "💎 Day 21",
            summary = "The classic 3-week mark. Compulsive autopilot loops have broken down. A new baseline of self-command is born.",
            neurobiology = "Synaptic plasticity favors your new lifestyle. The anterior cingulate cortex (the brain's willpower hub) shows measurable structural enhancement.",
            physicalBenefit = "Remarkable boost in sustained mental focus. Procrastination drops as dopamine sensitivity restores to natural goals.",
            mindsetShift = "Discipline is no longer something you force; it is becoming your default state of being. You respect yourself deeply.",
            recommendedAction = "Start a 5-minute morning gratitude or journaling ritual to cement your mental clarity."
        ),
        Milestone(
            dayThreshold = 30,
            title = "Cognitive Rebirth",
            phase = "Phase 3: Rewiring",
            badge = "🧠 Day 30",
            summary = "One full month of absolute clarity. Brain fog is replaced by razor-sharp cognitive recall, emotional calm, and deep focus.",
            neurobiology = "Substantial dopamine receptor density restoration. The prefrontal cortex now easily out-prioritizes primitive limbic impulses.",
            physicalBenefit = "Deep REM sleep cycles, enhanced physical endurance, and sustained metabolic vitality.",
            mindsetShift = "You are not the same person who started 30 days ago. Your attention span has expanded, and trivial distractions have lost power.",
            recommendedAction = "Review your major life goals for the next quarter. You now have the focus to execute them."
        ),
        Milestone(
            dayThreshold = 45,
            title = "Energy Transmutation",
            phase = "Phase 4: Elevation",
            badge = "⚡ Day 45",
            summary = "Internal energy is now effortlessly channeled into ambitious creative, athletic, and professional pursuits.",
            neurobiology = "Heightened emotional resilience. The brain's stress response (HPA axis) is well-regulated; anxiety under pressure is drastically lowered.",
            physicalBenefit = "High sustained vitality from dawn to dusk without caffeine dependency. Strong physical posture and commanding presence.",
            mindsetShift = "Patience is power. You realize that delayed gratification is the ultimate competitive advantage in the modern world.",
            recommendedAction = "Take on a difficult new challenge—learn a complex skill, train for an athletic event, or build a side venture."
        ),
        Milestone(
            dayThreshold = 60,
            title = "Identity Rebirth",
            phase = "Phase 4: Elevation",
            badge = "👑 Day 60",
            summary = "Two months clean. The old impulsive habits feel foreign, belonging to an old chapter of your past self.",
            neurobiology = "Default Mode Network (DMN) rewiring. The internal voice of doubt is quieted; rumination and negative self-talk are replaced by calm presence.",
            physicalBenefit = "Peak physical conditioning, robust immune resilience, and unwavering emotional baseline.",
            mindsetShift = "You do not fight urges anymore because you don't identify with them. You are a sovereign individual living with intention.",
            recommendedAction = "Share encouragement with someone who is struggling. Solidify your growth by lifting others."
        ),
        Milestone(
            dayThreshold = 90,
            title = "The Sovereign Reboot",
            phase = "Phase 5: Mastery",
            badge = "🏆 Day 90",
            summary = "The legendary 90-Day Reboot. Complete baseline restoration of your brain's neurochemical and reward architecture.",
            neurobiology = "Full neural reorganization. Complete upregulation of dopamine receptors back to healthy biological baselines.",
            physicalBenefit = "Complete renewal of vitality, profound sleep quality, optimal hormonal health, and boundless organic motivation.",
            mindsetShift = "You have conquered hyper-stimulation in an age of infinite distractions. You hold absolute dominion over your attention.",
            recommendedAction = "Celebrate this legendary milestone. Set a grand vision for your next year of mastery."
        ),
        Milestone(
            dayThreshold = 180,
            title = "Unshakable Sovereignty",
            phase = "Phase 6: Transcendence",
            badge = "🌟 Day 180",
            summary = "Half a year of pristine discipline. Ironclad self-control, unshakable focus, and deeply magnetic calm.",
            neurobiology = "Permanent consolidation of prefrontal gray matter volume and high cognitive efficiency.",
            physicalBenefit = "Peak physical and mental vitality. Radiant health, boundless stamina, and razor-sharp intellect.",
            mindsetShift = "Self-mastery is no longer an achievement you pursue; it is simply the air you breathe and the standard you uphold.",
            recommendedAction = "Reflect on how far you've ascended. Write a letter to your future self."
        ),
        Milestone(
            dayThreshold = 365,
            title = "Transcendent Mastery",
            phase = "Phase 6: Transcendence",
            badge = "⚜️ 1 Year",
            summary = "One full year of pure clarity. A total transformation of character, mind, body, and spirit.",
            neurobiology = "Pristine neurochemical balance and unmatched cognitive control.",
            physicalBenefit = "Elite physical and mental endurance. Total freedom from compulsive patterns.",
            mindsetShift = "You have mastered the most elusive art in human history: the total command of the self.",
            recommendedAction = "Live fully in the present, grounded in purpose, discipline, and infinite gratitude."
        )
    )

    fun getCurrentMilestone(days: Int): Milestone {
        return milestones.lastOrNull { it.dayThreshold <= days } ?: milestones.first()
    }

    fun getNextMilestone(days: Int): Milestone? {
        return milestones.firstOrNull { it.dayThreshold > days }
    }

    fun getProgressToNextMilestone(days: Int): Float {
        val current = getCurrentMilestone(days)
        val next = getNextMilestone(days) ?: return 1.0f

        val prevThreshold = if (days < current.dayThreshold) 0 else current.dayThreshold
        val range = (next.dayThreshold - prevThreshold).toFloat()
        val completed = (days - prevThreshold).toFloat()

        return (completed / range).coerceIn(0.0f, 1.0f)
    }

    fun getRankName(days: Int): String {
        return when {
            days < 3 -> "Initiate"
            days < 7 -> "Apprentice"
            days < 14 -> "Discipline Seeker"
            days < 30 -> "Mindful Warrior"
            days < 60 -> "Iron Vanguard"
            days < 90 -> "Sovereign Strategist"
            days < 180 -> "Master of Self"
            else -> "Enlightened Sovereign"
        }
    }
}
