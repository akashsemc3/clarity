# 🌿 Clarity — Minimalist Habit, Vitality & Streak Tracker

<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white" alt="Android" />
  <img src="https://img.shields.io/badge/Kotlin-2.0.0-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin" />
  <img src="https://img.shields.io/badge/UI-Jetpack_Compose_Material_3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white" alt="Jetpack Compose" />
</p>

> **A discreet, science-grounded self-mastery companion designed for neural rewiring, dopamine resensitization, and unyielding discipline.**
> 
> *Crafted with modern Android architecture: Jetpack Compose, Material 3, and interactive Home Screen Widgets.*

---

## 👨‍💻 Developed By
**Akash ([@akashsemc3](https://github.com/akashsemc3))**  
Feel free to connect on GitHub, open issues, or submit PRs!

---

## 📥 How to Install on Android

### Option A: Direct APK Download (Quickest)
1. Go to the **[Releases](../../releases)** section of this GitHub repository.
2. Download the latest `Clarity-v1.0.apk` onto your Android phone.
3. Tap the downloaded file to install.
   *(If prompted by Android, tap **Settings** and enable **"Allow from this source"**).*
4. Open **Clarity** and calibrate your clean days!

### Adding Widgets to Your Home Screen:
1. Long-press an empty space on your phone's home screen.
2. Tap **Widgets**.
3. Scroll to **Clarity** and drag either the **2×2 Counter** or **4×2 Motivation & Shield** widget onto your screen!

---

## ✨ Key Features

### 1. ⏱️ Live Real-Time Streak Engine
- **Live Counter**: Continuous ticking display tracking **Days, Hours, Minutes, and Seconds** of clean ascent.
- **Dynamic Circular Progress**: Visual arc showing your progress toward the next major milestone.
- **Adjustable Start Day**: Easily tap the streak circle to calibrate your start date if you began your journey earlier.
- **Reflection & Reset**: Stumbling is treated as neurological data, not failure. Prompts you to log the trigger (Boredom, Late Night Screen, Stress, Fatigue) and a lesson for next time.

### 2. 🌓 Dynamic Light & Dark Mode (Material 3)
- Seamless one-tap toggle between **AMOLED Dark Mode** and **Crisp Botanical Light Mode** directly from the top bar.
- Automatic system status and navigation bar adaptation.

### 3. 🧠 Progressive Scientific Benefits Roadmap
Benefits unlock dynamically as clean days accumulate, giving you clear physiological proof of your progress:
- **🌱 Day 1 (The Clean Slate)**: Dopamine D2 receptor rest cycle initiates; sleep architecture begins recalibration.
- **⚡ Day 3 (Overcoming Inertia)**: Restlessness peaks and drops; initial brain fog begins to thin; prefrontal inhibitory control kicks in.
- **🔥 Day 7 (The Vitality Surge)**: Proven natural spike in androgen receptor sensitivity; burst in physical energy, gym recovery, and mental sharpness.
- **🛡️ Day 14 (Neural Equilibrium)**: Social anxiety diminishes; eye contact becomes effortless; delta-FosB protein levels decline.
- **💎 Day 21 (The Habit Threshold)**: Initial habit loop broken; anterior cingulate cortex (willpower hub) strengthens; natural joys (music, nature, food) feel richer.
- **🧠 Day 30 (Cognitive Rebirth)**: Working memory and executive function operate at superior levels; dopamine baseline restored to healthy everyday stimuli.
- **⚡ Day 45 (Energy Transmutation)**: Internal fire effortlessly channeled into fitness, career goals, and creative endeavors.
- **👑 Day 60 (Identity Rebirth)**: Default Mode Network (DMN) rewires; self-doubt and compulsive urges feel foreign.
- **🏆 Day 90 (The Sovereign Reboot)**: Full neurochemical baseline reboot. Magnetic confidence, sharp focus, and deep emotional resilience.
- **🌟 Day 180 & 365 (Transcendent Mastery)**: Permanent identity transformation and iron self-command.

### 4. 📜 Curated Stoic & Neuroscientific Wisdom
- **60+ Deep Wisdom Quotes**: Insights from Stoic philosophers (Marcus Aurelius, Epictetus, Seneca), modern neuroscientists, and discipline champions (James Clear, Lao Tzu).
- **Daily Adaptive Rotation**: Quotes cycle daily or can be shuffled on demand with instant one-tap copy and share.

### 5. 🛡️ Emergency Urge Shield ("SOS Mode")
- **Interactive 4-4-4-4 Box Breathing Visualizer**: Guided animated breathing ring (Inhale 4s → Hold 4s → Exhale 4s → Hold 4s) to quickly lower basal cortisol, activate the parasympathetic nervous system, and halt compulsive impulses.
- **The 10-Minute Delay Timer**: Countdown timer grounded in behavioral neuroscience—90% of urges dissolve if you wait 10 minutes without feeding them.
- **Physical Environment Reset**: Actionable checklist (cold face wash to trigger the mammalian dive reflex, 20 pushups, changing rooms, putting phone away).

### 6. 📱 Home Screen Widgets
Two Material 3 widgets configured for Android launchers:
1. **Minimal Counter Widget (2×2 / 3×2)**:
   - Discreet "CLARITY" branding + Current Rank (e.g., "ASCENT").
   - Big bold "DAY 14" + live elapsed hours/minutes.
   - Smooth progress bar indicating distance to your next biological milestone.
2. **Motivation & Urge Shield Widget (4×2)**:
   - Day count & current milestone title ("Day 7 • Vitality Surge").
   - Daily stoic or neuroscience quote.
   - Quick one-tap **Emergency Shield** launcher button right on your home screen!

### 7. 📊 Analytics & Streak History
- Lifetime summary: Current Streak, Longest Record, Total Clean Days Logged, and Resets Logged.
- Chronological logs of every past streak, including recorded triggers and lessons learned.

---

## 🏗️ Architecture & Tech Stack
- **Framework**: Modern Android SDK (Target SDK 34 / Android 14)
- **UI Toolkit**: Jetpack Compose with Material 3 (Material You)
- **Async & Reactive Flow**: Kotlin Coroutines & StateFlow
- **Widgets**: Android AppWidgetProvider with RemoteViews (zero-crash whitelist-compliant XML)
- **CI/CD**: GitHub Actions workflow for automated APK releases
- **Local Storage**: Encrypted/Private SharedPreferences with Gson serialization

---
##📱 Screenshots

Clarity features a clean interface with both light and dark themes.
☀️ Light Mode
"Clarity Light Mode" (screenshots/lighthome.jpeg)
🌙 Dark Mode
"Clarity Dark Mode" (screenshots/dark1.jpeg)
"Clarity Dark Mode" (screenshots/dark2.jpeg)
"Clarity Dark Mode" (screenshots/dark3.jpeg)
"Clarity Dark Mode" (screenshots/dark4.jpeg)

📲 Home Screen Widget

"Clarity Widget" (screenshots/widget.jpeg)
---
## 🛠️ Technical Setup / Building from Source

1. Clone this repository:
   ```bash
   git clone https://github.com/akashsemc3/clarity.git
   ```
2. Open the project in **Android Studio**.
3. Let Gradle sync dependencies.
4. Run on an Android device or emulator (`Shift + F10`), or build an APK via terminal:
   ```bash
   ./gradlew assembleDebug
   ```
## 📄Copyright & Credits

© 2026 Akash
Clarity is an independent personal project conceived and developed with AI-assisted coding tools, including Google Antigravity.
The project direction, feature planning, design decisions, testing and development workflow were guided by me.
All rights reserved to the extent permitted by applicable law.
