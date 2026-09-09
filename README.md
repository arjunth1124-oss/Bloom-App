Bloom — PCOS Wellness & Habit TrackerBloom is a modern, native Android application built using Kotlin and Jetpack Compose. Designed specifically for holistic health management, it helps users track daily wellness goals (meals, movement, water intake, relaxation, sleep) and learn about PCOS through personalized daily tips and guided resources.✨ FeaturesMulti-Step Onboarding Flow: Custom guided setup powered by Jetpack DataStore to personalize user experience and persist onboarding status across app launches.Interactive Dashboard: Tracks core daily health metrics with quick log interactions and progress counters.Monetization Support: Seamless integration of Google AdMob Banner Ads within content feeds using Compose interop (AndroidView).Clean UI & Modern Design: Designed with custom typography, soft pastel color palettes, and responsive Jetpack Compose components.🛠 Tech Stack & ArchitectureLanguage: Kotlin (100%)UI Toolkit: Jetpack Compose (Declarative UI)Architecture: MVVM (Model-View-ViewModel) + Unidirectional Data Flow (UDF)Dependency Injection: Dagger-HiltNavigation: Jetpack Navigation ComposeLocal Persistence: Preferences DataStoreMonetization: Google Mobile Ads (AdMob) SDK📱 ScreenshotsOnboarding ScreenHome DashboardBanner Ad Integration(Add Screenshot)(Add Screenshot)(Add Screenshot)🚀 Getting StartedPrerequisitesAndroid Studio: Ladybug or newerMin SDK: 24 (Android 7.0)Target SDK: 34 or higherJDK: 17InstallationClone the repository:Bashgit clone https://github.com/your-username/BloomApp.git
Open the project in Android Studio.Sync project with Gradle files.Run the project on an Android Emulator or physical device (Shift + F10).📦 Project StructurePlaintextcom.example.bloomapp
├── data
│   ├── ads           # AdMob configuration and managers
│   └── datastore     # Preferences DataStore for onboarding persistence
├── ui
│   ├── home          # Dashboard screen & components (BannerAdView, Cards)
│   ├── navigation    # NavGraph & Screen definitions
│   ├── onboarding    # Multi-step Onboarding views & ViewModel
│   └── theme         # Color definitions, Typography, and Shapes
└── MainActivity.kt   # Entry point & NavGraph host
📄 LicensePlaintextDistributed under the MIT License. See LICENSE for more information.
