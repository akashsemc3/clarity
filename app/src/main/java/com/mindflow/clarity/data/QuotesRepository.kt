package com.mindflow.clarity.data

import kotlin.random.Random

object QuotesRepository {

    val quotes = listOf(
        WisdomQuote(
            1,
            "No man is free who is not master of himself.",
            "Epictetus",
            "Stoicism"
        ),
        WisdomQuote(
            2,
            "You have power over your mind - not outside events. Realize this, and you will find strength.",
            "Marcus Aurelius",
            "Stoicism"
        ),
        WisdomQuote(
            3,
            "We suffer more often in imagination than in reality.",
            "Seneca",
            "Stoicism"
        ),
        WisdomQuote(
            4,
            "The urge is just a temporary dopamine wave in your brain. Wait 10 minutes, and the wave will naturally collapse.",
            "Neuroscience Principle",
            "Neuroscience"
        ),
        WisdomQuote(
            5,
            "Dopamine is not the molecule of reward; it is the molecule of craving. Choose what you teach your brain to crave.",
            "Neuroscience Insight",
            "Neuroscience"
        ),
        WisdomQuote(
            6,
            "Every time you resist an impulse, your prefrontal cortex physically strengthens its inhibitory circuits.",
            "Behavioral Science",
            "Neuroscience"
        ),
        WisdomQuote(
            7,
            "Discipline is choosing between what you want now and what you want most.",
            "Abraham Lincoln",
            "Discipline"
        ),
        WisdomQuote(
            8,
            "Waste no more time arguing what a good man should be. Be one.",
            "Marcus Aurelius",
            "Stoicism"
        ),
        WisdomQuote(
            9,
            "He who conquers others is strong; he who conquers himself is mighty.",
            "Lao Tzu",
            "Mastery"
        ),
        WisdomQuote(
            10,
            "First say to yourself what you would be; and then do what you have to do.",
            "Epictetus",
            "Stoicism"
        ),
        WisdomQuote(
            11,
            "To be calm is the highest achievement of the self.",
            "Zen Proverb",
            "Mindfulness"
        ),
        WisdomQuote(
            12,
            "The impediment to action advances action. What stands in the way becomes the way.",
            "Marcus Aurelius",
            "Stoicism"
        ),
        WisdomQuote(
            13,
            "You don't rise to the level of your goals, you fall to the level of your systems.",
            "James Clear",
            "Discipline"
        ),
        WisdomQuote(
            14,
            "Small disciplines repeated with consistency every day lead to great achievements gained slowly over time.",
            "John C. Maxwell",
            "Discipline"
        ),
        WisdomQuote(
            15,
            "A champion is defined not by their wins but by how they can recover when they fall.",
            "Serena Williams",
            "Resilience"
        ),
        WisdomQuote(
            16,
            "The secret of change is to focus all your energy not on fighting the old, but on building the new.",
            "Socrates",
            "Philosophy"
        ),
        WisdomQuote(
            17,
            "Self-control is strength. Right thought is mastery. Calmness is power.",
            "James Allen",
            "Mastery"
        ),
        WisdomQuote(
            18,
            "If you want to conquer the anxiety of life, live in the moment, live in the breath.",
            "Amit Ray",
            "Mindfulness"
        ),
        WisdomQuote(
            19,
            "When you feel like giving up, remember why you held on for so long.",
            "Anonymous",
            "Resilience"
        ),
        WisdomQuote(
            20,
            "Energy cannot be created or destroyed; it can only be transformed from one form to another.",
            "Albert Einstein",
            "Transmutation"
        ),
        WisdomQuote(
            21,
            "Your body can stand almost anything; it’s your mind that you have to convince.",
            "Discipline Principle",
            "Discipline"
        ),
        WisdomQuote(
            22,
            "Between stimulus and response there is a space. In that space is our power to choose our response.",
            "Viktor Frankl",
            "Mastery"
        ),
        WisdomQuote(
            23,
            "The soul becomes dyed with the color of its thoughts.",
            "Marcus Aurelius",
            "Stoicism"
        ),
        WisdomQuote(
            24,
            "Comfort is the enemy of progress. Embrace the friction.",
            "Modern Stoic",
            "Discipline"
        ),
        WisdomQuote(
            25,
            "The craving will pass whether you indulge it or not. Indulging resets your timer; enduring builds your crown.",
            "Clarity Maxim",
            "Mastery"
        )
    )

    fun getDailyQuote(dayOfStreak: Int): WisdomQuote {
        if (quotes.isEmpty()) return WisdomQuote(0, "Stay disciplined.", "Clarity", "Discipline")
        val index = (dayOfStreak.coerceAtLeast(0) + (System.currentTimeMillis() / (1000 * 60 * 60 * 24)).toInt()) % quotes.size
        return quotes[Math.abs(index)]
    }

    fun getRandomQuote(): WisdomQuote {
        return quotes[Random.nextInt(quotes.size)]
    }
}
