# Calibrey | Premium NCERT AI Educational Operating System

<div align="center">

![Platform](https://img.shields.io/badge/Platform-Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0+-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20M3-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)
![Database](https://img.shields.io/badge/Database-Room%202.7-F58220?style=for-the-badge&logo=sqlite&logoColor=white)
![AI Engine](https://img.shields.io/badge/AI-Google%20Gemini-8E75C2?style=for-the-badge&logo=googlegemini&logoColor=white)
[![Download APK](https://img.shields.io/badge/Direct_Download-APK%20(v1.0)-00C853?style=for-the-badge&logo=android&logoColor=white)](release/Calibrey-v1.0.apk?raw=true)
![License](https://img.shields.io/badge/License-Apache%202.0-blue?style=for-the-badge)
![PRs Welcome](https://img.shields.io/badge/PRs-Welcome-brightgreen?style=for-the-badge)

<p align="center">
  <b>An Apple-grade, liquid-glass monochrome academic operating system engineered for NCERT secondary and senior secondary students (Classes 9 to 12).</b>
</p>

[📥 Download APK (v1.0)](release/Calibrey-v1.0.apk?raw=true) • [Quick Start](#-quick-start) • [Installation Guide (APK)](#-apk-installation-guide) • [Architecture](#-technical-architecture) • [Design System](#-liquid-glass-monochrome-aesthetic) • [Documentation](docs/)

</div>

> ### ⚡ Instant APK Download
> Want to try Calibrey on your Android phone immediately?
> - **Direct Download**: **[`Calibrey-v1.0.apk`](release/Calibrey-v1.0.apk?raw=true)** (Universal APK, 26 MB)
> - **Installation Steps**: Tap the link above to download $\rightarrow$ Open in Files $\rightarrow$ Tap Install. For details, refer to the [APK Installation Guide](docs/APK_INSTALLATION_GUIDE.md).

---

## 📖 Executive Summary

**Calibrey** is an enterprise-grade, privacy-first academic operating system developed for students preparing for CBSE board examinations and foundational scientific competitive tests (JEE / NEET). 

Unlike conventional gamified learning apps filled with distracting cartoons and superficial mock data, Calibrey delivers a **focused, distraction-free environment** modeled after high-performance productivity tools like Linear, Apple Health, and modern macOS design. It combines structured curricular hierarchies, interactive concept graphs, spaced repetition memory schedules, and a Socratic AI Tutor powered by the Google Gemini API.

---

## 🌟 Core System Pillars

### 1. 🏛️ Comprehensive NCERT Curricular Engine
- **Classes Supported**: Class 9, Class 10, Class 11 (Science), and Class 12 (Science).
- **Multi-Discipline Coverage**: Physics, Chemistry, Biology, and Mathematics.
- **Hierarchical Depth**: Subjects $\rightarrow$ Units $\rightarrow$ Chapters $\rightarrow$ Subtopics $\rightarrow$ Formula Sheets $\rightarrow$ Exemplar Numerical Sets.
- **Granular Progress Matrix**: Real-time subtopic completion checkoffs backed by on-device SQLite state machines.

### 2. 🤖 Socratic AI Tutor & Doubt Solver (Gemini Integration)
Integrated with Google Gemini models with targeted pedagogical system instructions:
- **Concept Breakdown**: Socratic derivation from first principles rather than direct answers.
- **Step-by-Step Exercise Solver**: Textbook numerical resolution with unit conversions and mathematical justifications.
- **CBSE Board Exam Drill**: Rigorous generation of 3-mark and 5-mark examination problems with official CBSE marking scheme hints.
- **Strategic Revision Planner**: Dynamic daily schedules calculated against board exam countdown deadlines.

### 3. 🧠 Synaptic Interactive Knowledge Graph
- **Dynamic Node Web**: Canvas-rendered, physics-inspired interactive network mapping conceptual dependencies across science and mathematics.
- **Real-Time Node Telemetry**: Pulsing halos denote chapters with high memory decay or overdue revision.
- **One-Tap Deep Navigation**: Directly launch active recall quizzes or formula reviews from any node on the canvas.

### 4. 📈 Cognitive Study DNA & Consistency Heatmap
- **8-Week Activity Matrix**: GitHub-style density grid displaying continuous daily study volume and problem resolution.
- **Behavioral Vectors**:
  - **Learning Velocity**: Rate of concept intake and formula comprehension.
  - **Retention Index**: Active recall accuracy across scheduled interval reviews.
  - **Rhythm Stability**: Streak persistence and regularity across study blocks.
  - **Predicted Board Exam Score**: Statistical projection based on NCERT exemplar mastery.

### 5. ⏱️ Spaced Repetition Engine & Exam War Room
- **Hermann Ebbinghaus Forgetting Curve**: Automated triage algorithm categorizing syllabus units into *Overdue*, *Recommended*, and *Mastered*.
- **Active Recall Flashcards**: Interactive flip cards testing definitions, SI units, and chemical reaction equations.
- **20-Minute Focus Sprints**: Low-friction countdown timers engineered for deep cognitive sprints without notification interference.

### 6. 🛡️ Authentic Academic Identity (Zero Fake Data)
- **Real Onboarding Portal**: Zero pre-filled fake usernames or dummy accounts. Students initialize their real name, grade level, and academic insignia.
- **Role Isolation**: Dedicated configuration profiles for **Students** and **Educators / Mentors**.
- **Teacher Command Center**: Classroom roster analytics, weak-chapter broadcasts, and syllabus pacing metrics.
- **Privacy First**: 100% on-device data persistence via Room SQLite with destructive migration safety and zero background trackers.

---

## 💎 Liquid-Glass Monochrome Aesthetic

Calibrey rejects bright cartoon colors and casual emojis in favor of an **obsidian liquid-glass design system** inspired by high-end luxury instrumentation:

| Element | Specification | Visual Experience |
| :--- | :--- | :--- |
| **Canvas Background** | Deep Obsidian (`#08090C` to `#050508`) | Eliminates OLED battery drain and eye strain |
| **Glass Surfaces** | Translucent Acrylic (`rgba(255,255,255, 0.05-0.12)`) | Subtle depth with physical layer hierarchy |
| **Specular Bevels** | Sub-pixel Gradient Sheens (`1.dp` stroke) | Light catches top edge surfaces naturally |
| **Active States** | Solid Titanium White (`#FFFFFF`) on Obsidian | Uncompromised readability and tactile contrast |
| **Iconography** | Vector Material Symbols | Replaces casual emojis with authoritative technical iconography |

---

## 📱 APK Installation Guide

For students, educators, and testers looking to install Calibrey directly onto an Android device:

### Option A: Direct Installation via Android Studio / CLI
1. Ensure your physical device has **Developer Options** and **USB Debugging** enabled.
2. Connect your device via USB or wireless debugging.
3. Build and install the debug APK directly:
   ```bash
   gradle installDebug
   ```

### Option B: Sideloading the Standalone APK
1. Compile the APK package:
   ```bash
   gradle assembleDebug
   ```
2. The generated APK will be located at:
   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```
3. Transfer `app-debug.apk` to your Android device (via Google Drive, USB, or AirDroid).
4. Tap the APK file in your phone's File Manager.
5. If prompted, toggle **"Allow from this source"** under **Install Unknown Apps**.
6. Follow the on-screen prompt and tap **Install**.
7. Open **Calibrey** from your launcher!

> For full illustrated troubleshooting and distribution steps, see the dedicated [APK Installation Guide](docs/APK_INSTALLATION_GUIDE.md).

---

## 🏗️ Technical Architecture

Calibrey follows modern Android Jetpack architecture guidelines using the **MVVM (Model-View-ViewModel)** pattern paired with unidirectional data flow (UDF):

```
├── app/src/main/java/com/example/
│   ├── MainActivity.kt               # Single Activity, edge-to-edge system insets
│   │
│   ├── ai/
│   │   └── CalibreyAiService.kt      # Google Gemini 1.5/2.0 REST client & prompt pipelines
│   │
│   ├── data/
│   │   ├── CalibreyRepository.kt     # Single Source of Truth coordinating Room & memory
│   │   ├── curriculum/
│   │   │   └── NcertCurriculumData.kt# Authoritative NCERT syllabus & assessment banks
│   │   └── local/
│   │       ├── CalibreyDao.kt        # Coroutine & Flow-enabled Room DAO interfaces
│   │       ├── CalibreyDatabase.kt   # Room Database configuration with schema tracking
│   │       └── Entities.kt           # Room persistence models (Profile, Progress, Quizzes)
│   │
│   ├── model/
│   │   ├── CurriculumModels.kt       # Subjects, Chapters, Subtopics, Quizzes
│   │   ├── StudyDnaModels.kt         # Cognitive vectors, heatmap entries, knowledge nodes
│   │   └── UserProfile.kt            # Student profile, NCERT grades, insignias
│   │
│   └── ui/
│       ├── CalibreyViewModel.kt      # State management, coroutine orchestration, navigation
│       ├── components/
│       │   ├── CinematicIntro.kt     # High-framerate brand intro sequence
│       │   ├── EmbeddedVideoCard.kt  # Liquid-glass video player & lecture notes
│       │   ├── GlassComponents.kt    # Standardized vitreous cards, buttons, docks, tabs
│       │   ├── KnowledgeGraphView.kt # Interactive Canvas synaptic network
│       │   └── StudyHeatmapView.kt   # GitHub-style 8-week learning density matrix
│       ├── screens/
│       │   ├── AiRevisionNotesScreen.kt # One-click AI formula sheets & revision summaries
│       │   ├── AiTutorScreen.kt      # Apple-grade conversational tutor with 4 modes
│       │   ├── ChapterDetailScreen.kt# Subtopic checklist, exemplar formulas, video cards
│       │   ├── CurriculumScreen.kt   # Class & subject selector with instant search
│       │   ├── DashboardScreen.kt    # Central flight deck: War Room & Study DNA
│       │   ├── ExamWarRoomScreen.kt  # Focus countdowns, 20-min sprints, syllabus dial
│       │   ├── KnowledgeGraphScreen.kt# Full-screen interactive concept dependency tree
│       │   ├── OnboardingScreen.kt   # Authentic student registration & grade binding
│       │   ├── ProfileDialog.kt      # Profile switcher, role manager, and session sign-out
│       │   ├── QuizScreen.kt         # Timed assessments, feedback, and GP animations
│       │   ├── RevisionCenterScreen.kt# Spaced repetition decay queue & flashcards
│       │   ├── SocialLeaderboardScreen.kt# Peer circles & Teacher command roster
│       │   └── StudyDnaScreen.kt     # Cognitive telemetry & AI learning prescriptions
│       └── theme/
│           ├── Color.kt              # Liquid-glass obsidian palette & specular alpha stops
│           ├── Theme.kt              # Dynamic Material 3 dark color scheme
│           └── Type.kt               # Academic typographic scale & high-contrast styles
```

---

## ⚡ Tech Stack

| Technology | Purpose |
| :--- | :--- |
| **Kotlin 2.0+** | Modern, concise, null-safe language foundation |
| **Jetpack Compose (M3)** | Declarative UI toolkit implementing Material Design 3 |
| **Room Database 2.7** | SQLite local persistence with Coroutines & StateFlow support |
| **KSP (Kotlin Symbol Processing)** | High-speed annotation processing for Room entities |
| **Google Gemini API** | Multi-turn reasoning for Socratic doubt resolution |
| **Kotlin Coroutines & Flow** | Asynchronous pipelines and reactive UI state streaming |
| **OkHttp / Retrofit** | Resilient network communication with TLS 1.3 |
| **Secrets Gradle Plugin** | Secure compile-time environment variable injection (`BuildConfig`) |
| **Robolectric & JUnit 4** | Local JVM unit testing for Critical User Journeys (CUJs) |

---

## 🚀 Quick Start & Development Setup

### Prerequisites
- **Android Studio**: Koala / Ladybug or newer.
- **JDK**: Version 17 or Version 21.
- **Android SDK**: `minSdk 24` (Android 7.0) up to `targetSdk 36` (Android 15+).

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/calibrey-android.git
cd calibrey-android
```

### 2. Configure Environment Variables
Copy `.env.example` to `.env`:
```bash
cp .env.example .env
```
Edit `.env` and add your Google Gemini API key:
```ini
GEMINI_API_KEY=your_actual_gemini_api_key_here
```
*(Alternatively, leave empty during build and enter your key at runtime via Calibrey's Profile Settings screen).*

### 3. Build & Run
```bash
# Verify app compilation
gradle assembleDebug

# Run unit tests
gradle :app:testDebugUnitTest
```

---

## 🔒 Security & Privacy Policy

- **No Remote Telemetry or Ad Trackers**: Calibrey operates with zero third-party analytics SDKs.
- **Zero Broad Storage Permissions**: Does not request or require `READ_EXTERNAL_STORAGE` or `READ_MEDIA_IMAGES`.
- **Compile-Time Secret Shield**: API credentials in `.env` are injected into `BuildConfig` and excluded from source control.
- **Local Data Governance**: All student notes, quiz logs, and study streaks remain securely inside private SQLite database files on the device.

---

## 🤝 Contributing

We welcome contributions from students, teachers, and Android engineers! Please review our guidelines before submitting a pull request:

- [Contributing Guidelines](CONTRIBUTING.md)
- [Code of Conduct](CODE_OF_CONDUCT.md)
- [Security Policy](SECURITY.md)
- [Architecture Deep Dive](docs/ARCHITECTURE.md)
- [Design System Guide](docs/DESIGN_SYSTEM.md)

---

## 📄 License

```
Copyright 2026 Calibrey Learning Technologies

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

<div align="center">
  <sub>Built with precision for students shaping the scientific future.</sub>
</div>
