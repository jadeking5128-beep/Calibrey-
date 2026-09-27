# Calibrey Architectural Blueprint

## 🏛️ High-Level System Architecture

Calibrey is engineered following **Unidirectional Data Flow (UDF)** and Google's recommended **Clean Architecture / MVVM (Model-View-ViewModel)** pattern on Android.

```
┌─────────────────────────────────────────────────────────────┐
│                       UI Layer (M3)                         │
│  Jetpack Compose Screens • GlassComponents • Canvas Views   │
└──────────────────────────────▲──────────────────────────────┘
                               │ StateFlow<UiState>
                               │ Events / User Actions
┌──────────────────────────────▼──────────────────────────────┐
│                    ViewModel Layer                          │
│     CalibreyViewModel • Coroutine Scopes • State Machine    │
└──────────────────────────────▲──────────────────────────────┘
                               │ Repository APIs
┌──────────────────────────────▼──────────────────────────────┐
│                     Domain & Data Layer                     │
│                  CalibreyRepository (SSOT)                  │
└───────────────▲──────────────────────────────▲──────────────┘
                │                              │
┌───────────────▼──────────────┐┌──────────────▼──────────────┐
│     Room Database (Local)    ││    CalibreyAiService        │
│   CalibreyDao • SQLite DB    ││  Google Gemini 1.5/2.0 API  │
│ UserProfile • Progress • Quiz││ Socratic System Prompts     │
└──────────────────────────────┘└─────────────────────────────┘
```

---

## 🧩 Architectural Layers

### 1. Presentation Layer (`com.example.ui`)
- **Jetpack Compose**: 100% declarative UI with zero legacy XML views or fragments.
- **Single Activity Model**: `MainActivity` hosts the whole application lifecycle and delegates to Compose navigation states.
- **Liquid-Glass Design Primitives (`ui.components.GlassComponents`)**:
  - `GlassCard`: Acrylic dark translucent container with specular linear top bevel highlight.
  - `GlassSegmentedControl`: Apple-grade tab switcher with high contrast titanium white active state.
  - `GlassButton`: Tactile obsidian button with custom ripple feedback.
  - `GlassStatusPill`: Geometric status indicator replacing casual emojis.
- **Custom Canvas Rendering**:
  - `KnowledgeGraphView`: Renders dynamic conceptual nodes with Bézier curve interconnects, pulsing rings, and trigonometric orbital layouts.
  - `StudyHeatmapView`: GitHub-style 8-week matrix calculating opacity bins from local activity telemetry.

### 2. State & Business Logic Layer (`ui.CalibreyViewModel`)
- **Single ViewModel Orchestration**: Coordinates reactive state across navigation tabs (Dashboard, Curriculum, AI Tutor, Knowledge Graph, War Room, Social).
- **Safe Concurrency**: All database writes, API dispatches, and timer countdowns are executed inside `viewModelScope` using Kotlin Coroutines and structured concurrency.
- **StateFlow Persistence**: Exposes observable, lifecycle-aware `StateFlow` primitives to Composables, preventing memory leaks and unnecessary recompositions.

### 3. Repository Layer (`data.CalibreyRepository`)
- **Single Source of Truth (SSOT)**: The repository mediates between on-device SQLite persistence and external Gemini AI services.
- **Reactive Data Pipelines**: Emits Flow streams directly from Room DAOs so UI components update automatically when progress, quiz scores, or profiles change.
- **Destructive Migration Resilience**: Configured with automated fallback migration strategies to prevent schema mismatch crashes across version updates.

### 4. Persistence Layer (`data.local`)
- **Room 2.7 with SQLite**:
  - `user_profile`: Tracks user identity, role, grade, Gravity Points (GP), and API key preferences.
  - `chapter_progress`: Tracks completion percentage, formula sheet reviews, and subtopic checklists.
  - `quiz_results`: Historical record of assessment scores and accuracy.
  - `revision_notes`: User-saved and AI-generated exam formulas and notes.
- **Thread Safety**: All database interactions run on `Dispatchers.IO` via Kotlin Coroutines.

### 5. AI Integration Layer (`ai.CalibreyAiService`)
- **Gemini REST Client**: Communicates directly with Google Gemini endpoints (`gemini-1.5-flash` / `gemini-2.0-flash`).
- **Four Pedagogical Prompt Pipelines**:
  1. `CONCEPT_BREAKDOWN`: Socratic dialectic leading students to discover principles inductively.
  2. `DOUBT_SOLVER`: Step-by-step textbook numerical solutions with physical reasoning and SI checks.
  3. `EXAM_DRILL`: Formal CBSE board exam questions categorized by marking tiers (1-mark, 3-mark, 5-mark).
  4. `STUDY_PLANNER`: Dynamic calendar sequencing based on exam deadlines and syllabus weightage.
- **Security**: Injects keys dynamically from `BuildConfig` with user-override options stored securely in encrypted private app storage.

---

## 🛡️ Data Integrity & Concurrency Guarantees

1. **Unidirectional Data Flow**: State flows down; events bubble up.
2. **Immutable Models**: Domain models in `model/` are defined as Kotlin `data class` types with read-only properties (`val`).
3. **No Coroutine Leaks**: Background timers and network tasks are bound to structured coroutine scopes and cancelled immediately upon screen departure.
4. **Room Database Hash Verification**: Strict version synchronization between Room entity schemas and database configuration ensures zero integrity exceptions.
