# Calibrey: Premium NCERT AI Educational Operating System

Calibrey is an AI-powered educational operating system engineered specifically for NCERT secondary and senior secondary students (Classes 9 through 12). Designed with an Apple-grade monochrome liquid-glass visual architecture, Calibrey integrates structured curricular learning, Socratic AI tutoring, spaced repetition revision, cognitive behavioral analytics (Study DNA), and teacher oversight into a unified academic platform.

---

## Key System Pillars

### 1. Liquid-Glass Monochrome Design Language
- **Vitreous Obsidian Surfaces**: Multi-layer alpha depth using gradient sheens and specular light reflections that catch the top bevel of curved glass.
- **Apple-Grade Precision**: Segmented controls with high-contrast monochrome states (pure titanium white active pills with obsidian typography), refined typography hierarchy, and sub-pixel borders.
- **Zero Decorative Noise**: Complete elimination of casual emojis in favor of crisp Material Symbols, geometric indicators, and typographic telemetry.

### 2. Professional Academic Authentication
- **Onboarding Gate**: Zero pre-populated fake users. Students initialize their real academic identity via the Academic Authentication Portal.
- **Role Segregation**: Independent configurations for Students and Educators/Mentors.
- **Curriculum Grade Selection**: Dynamic curriculum binding for NCERT Class 9, Class 10, Class 11 (Science), and Class 12 (Science).
- **Academic Insignias**: Monogram insignia insignias (`SC`, `MT`, `PH`, `CH`, `BI`, `CB`) representing foundational disciplines.
- **Session Management**: Full profile switching and on-device session sign-out via Room SQLite database.

### 3. Comprehensive NCERT Curriculum Hierarchy
- **Deep Content Hierarchy**: Subjects -> Chapters -> Subtopics -> Formulas -> Exemplar Problems -> Active Recall Quizzes.
- **Subtopic Precision Tracking**: Granular mastery checkboxes with incremental Gravity Point (GP) incentives.
- **Embedded Lecture Integration**: Curriculum-aligned video lectures with playback cards and takeaway formulas.
- **Bookmarking & Search**: Full-text searching across chapter titles, NCERT concepts, and governing laws.

### 4. Socratic AI Tutor & Doubt Resolution (Gemini Integration)
- **Four Specialized Pedagogical Modes**:
  - *Concept Breakdown*: First-principles NCERT explanations.
  - *Doubt Solver*: Step-by-step resolution of textbook exercises and numericals.
  - *Board Exam Drill*: Formatted 3-mark and 5-mark CBSE board exam questions with official marking scheme hints.
  - *Study Planner*: Strategic revision scheduling based on syllabus deadlines.
- **Flexible AI Integration**: Automatically leverages configured system secrets or allows students/institutions to bring their own Gemini API key.

### 5. Adaptive Spaced Repetition Engine
- **Decay Tracking**: Automated scheduling prioritizing chapters with high memory decay rates.
- **Triage Queues**: High-urgency ("Overdue"), "Recommended", and "Mastered" categorizations.
- **Active Recall Flashcards**: Interactive daily flip cards testing core definitions and scientific principles.

### 6. Cognitive Study DNA
- **Multi-Vector Telemetry**:
  - *Learning Velocity*: Speed of concept intake across chapters.
  - *Retention Index*: Active recall success rate during revision quizzes.
  - *Rhythm Stability*: Habit persistence and study streak consistency.
  - *Predicted Board Score Bracket*: Statistical projection based on quiz accuracy and exemplar coverage.
- **Curricular Mastery Spectrum**: Individual mastery bars for Chemistry, Physics, Biology, Algebra, and Geometry.

### 7. Study Consistency Heatmap
- **8-Week Activity Matrix**: GitHub-style high-density learning matrix reflecting minutes logged and assessments completed.
- **Rhythm Analytics**: Interactive day tooltips showcasing daily productivity patterns.

### 8. Interactive Synaptic Knowledge Graph
- **Concept Web Visualization**: Chapters and topics rendered as interconnected synaptic nodes with dependency vectors.
- **Live State Indication**: Mastery percentages, revision urgency halos, and instant curriculum jumps.

### 9. Social Learning & Teacher Command Center
- **Academic Leaderboards**: Global and grade-level rankings driven by earned Gravity Points.
- **Peer Study Circle**: Direct academic forum for sharing formulas, proofs, and study notes.
- **Teacher Oversight**: Classroom roster metrics, class average GP, syllabus pacing, and critical weak-topic alert broadcasts.

---

## Technical Architecture

```
com.example
├── MainActivity.kt                  # Edge-to-edge entry point with authentication gates
├── ai/
│   └── CalibreyAiService.kt        # Gemini REST API integration & pedagogical prompt chains
├── data/
│   ├── CalibreyRepository.kt       # Single source of truth coordinating Room & in-memory state
│   ├── curriculum/
│   │   └── NcertCurriculumData.kt  # Rich NCERT Class 9-12 curriculum content & quiz sets
│   └── local/
│       ├── CalibreyDao.kt          # Room Database DAOs
│       ├── CalibreyDatabase.kt     # Room Database definition & destructive migration config
│       └── Entities.kt             # Room persistence schemas (Profile, Progress, Quizzes, Notes)
├── model/
│   ├── CurriculumModels.kt         # Subjects, Chapters, Subtopics, Quizzes
│   ├── StudyDnaModels.kt           # Heatmap, Knowledge Nodes, Missions, Achievements
│   └── UserProfile.kt              # Student identity, NCERT class, and level progression
└── ui/
    ├── CalibreyViewModel.kt        # ViewModel orchestrating navigation, AI calls, and state
    ├── components/
    │   ├── CinematicIntro.kt       # Application startup branding sequence
    │   ├── EmbeddedVideoCard.kt    # Lecture media player component
    │   ├── GlassComponents.kt      # Liquid-glass cards, buttons, segmented controls, docks
    │   ├── KnowledgeGraphView.kt   # Interactive Canvas-based synaptic concept web
    │   └── StudyHeatmapView.kt     # 8-week matrix consistency visualization
    ├── screens/
    │   ├── AiRevisionNotesScreen.kt# Gemini revision notes & formula sheet generator
    │   ├── AiTutorScreen.kt        # Socratic conversation interface with mode selection
    │   ├── ChapterDetailScreen.kt  # Subtopic checklist, video, and formula sheets
    │   ├── CurriculumScreen.kt     # Subject switcher, chapter catalog, and search
    │   ├── DashboardScreen.kt      # Command center with War Room & Study DNA modules
    │   ├── ExamWarRoomScreen.kt    # Countdown timer, syllabus gauge, and 20-min sprints
    │   ├── KnowledgeGraphScreen.kt # Full-page curriculum concept navigator
    │   ├── OnboardingScreen.kt     # Professional academic authentication & profile setup
    │   ├── ProfileDialog.kt        # Academic identity settings and session sign-out
    │   ├── QuizScreen.kt           # Interactive NCERT assessments & GP reward animations
    │   ├── RevisionCenterScreen.kt # Adaptive spaced repetition queue & flashcards
    │   ├── SocialLeaderboardScreen.kt # Competitive leaderboards & teacher oversight
    │   └── StudyDnaScreen.kt       # Cognitive telemetry & AI action plans
    └── theme/
        ├── Color.kt                # Monochrome liquid-glass color definitions
        ├── Theme.kt                # Material 3 dark color scheme
        └── Type.kt                 # High-legibility typography system
```

---

## Configuration & Environment Variables

Calibrey integrates with the Google Gemini API to power the Socratic AI Tutor and the Revision Notes Generator.

1. **AI Studio Secrets Configuration**:
   Add your Gemini API key in the AI Studio **Secrets** panel under the name:
   ```
   GEMINI_API_KEY
   ```
2. **Local Environment (`.env`)**:
   ```
   GEMINI_API_KEY=your_gemini_api_key_here
   ```
3. **Optional User-Provided Key**:
   Students and mentors can also enter a custom key directly within the app via **Onboarding** or **Profile Settings**.

---

## Building and Testing

Calibrey is built using the Gradle Kotlin DSL (`build.gradle.kts`):

```bash
# Execute local unit tests (Robolectric & JUnit)
gradle :app:testDebugUnitTest

# Assemble Debug APK
gradle assembleDebug
```

---

## Security & Play Policy Compliance

- **Zero Broad Storage Permissions**: The app requires zero external storage permissions (`READ_EXTERNAL_STORAGE` / `READ_MEDIA_IMAGES`).
- **Encrypted Local Storage**: Student academic telemetry and progress are stored on-device via Room SQLite.
- **No Static Fake Placeholders**: Profiles and progress are earned dynamically through active recall assessments.
- **Accessible UI**: Minimum 48dp touch targets on all interactive components with high contrast monochrome readability.
