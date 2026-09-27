package com.example.data

import com.example.data.curriculum.NcertCurriculumData
import com.example.data.local.*
import com.example.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlin.random.Random

class CalibreyRepository(
  private val dao: CalibreyDao,
  private val scope: CoroutineScope
) {
  private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

  // Profile Flow
  val userProfile: StateFlow<UserProfile> = dao.getUserProfile()
    .map { entity ->
      if (entity == null || entity.fullName.isBlank() || !entity.isAuthenticated) {
        UserProfile(isAuthenticated = false)
      } else {
        val ncertClass = runCatching { NcertClass.valueOf(entity.ncertClass) }.getOrDefault(NcertClass.CLASS_10)
        val role = runCatching { UserRole.valueOf(entity.role) }.getOrDefault(UserRole.STUDENT)
        UserProfile(
          id = entity.id,
          fullName = entity.fullName,
          role = role,
          ncertClass = ncertClass,
          avatarId = entity.avatarId,
          geminiApiKey = entity.geminiApiKey,
          gravityPoints = entity.gravityPoints,
          currentStreak = entity.currentStreak,
          bestStreak = entity.bestStreak,
          level = UserProfile.calculateLevel(entity.gravityPoints),
          rankTitle = UserProfile.calculateRank(entity.gravityPoints),
          totalMinutesStudied = entity.totalMinutesStudied,
          isAuthenticated = true
        )
      }
    }
    .stateIn(scope, SharingStarted.Eagerly, UserProfile(isAuthenticated = false))

  // Progress Flows
  val chapterProgressMap: StateFlow<Map<String, ChapterProgressEntity>> = dao.getAllChapterProgress()
    .map { list -> list.associateBy { it.chapterId } }
    .stateIn(scope, SharingStarted.Eagerly, emptyMap())

  val subtopicProgressMap: StateFlow<Map<String, SubtopicProgressEntity>> = dao.getAllSubtopicProgress()
    .map { list -> list.associateBy { it.subtopicId } }
    .stateIn(scope, SharingStarted.Eagerly, emptyMap())

  val quizHistory: StateFlow<List<QuizAttempt>> = dao.getAllQuizHistory()
    .map { list ->
      list.map {
        QuizAttempt(
          id = it.id.toString(),
          quizId = it.quizId,
          chapterId = it.chapterId,
          chapterTitle = it.chapterTitle,
          score = it.score,
          totalQuestions = it.totalQuestions,
          earnedGp = it.earnedGp,
          timestamp = it.timestamp
        )
      }
    }
    .stateIn(scope, SharingStarted.Eagerly, emptyList())

  val savedNotes: StateFlow<List<SavedNote>> = dao.getAllSavedNotes()
    .map { list ->
      list.map {
        SavedNote(
          id = it.id,
          chapterId = it.chapterId,
          chapterTitle = it.chapterTitle,
          subjectName = it.subjectName,
          noteType = runCatching { NoteType.valueOf(it.noteType) }.getOrDefault(NoteType.SUMMARY),
          content = it.content,
          createdAt = it.createdAt
        )
      }
    }
    .stateIn(scope, SharingStarted.Eagerly, emptyList())

  val activityLogs: StateFlow<List<HeatmapDay>> = dao.getRecentActivity()
    .map { list ->
      if (list.isEmpty()) {
        generateInitialActivityLogs()
      } else {
        list.map {
          HeatmapDay(
            date = it.date,
            intensity = it.intensity,
            minutesStudied = it.minutesStudied,
            quizzesCompleted = it.quizzesTaken,
            dayOfWeek = "Day"
          )
        }
      }
    }
    .stateIn(scope, SharingStarted.Eagerly, generateInitialActivityLogs())

  // Dynamic Daily Missions
  private val _dailyMissions = MutableStateFlow(
    listOf(
      DailyMission("m1", "Master 1 NCERT Quiz", "Score 80%+ on any chapter quiz", 1, 0, 100, false, MissionType.QUIZ),
      DailyMission("m2", "Spaced Repetition Review", "Revise 1 overdue topic in Revision Center", 1, 0, 80, false, MissionType.REVISE),
      DailyMission("m3", "Socratic AI Doubt Session", "Clarify a conceptual doubt with Calibrey AI", 1, 1, 60, true, MissionType.AI_TUTOR),
      DailyMission("m4", "Deep Study Sprint", "Complete 30 minutes of focused NCERT study", 30, 25, 120, false, MissionType.STUDY_TIME)
    )
  )
  val dailyMissions: StateFlow<List<DailyMission>> = _dailyMissions.asStateFlow()

  // Social & Friends
  private val _friends = MutableStateFlow(
    listOf(
      Friend("f1", "Rohan Verma", 2, NcertClass.CLASS_10, 2340, 12, "Stellar Scholar", true, "Solving Quadratic Equations"),
      Friend("f2", "Ananya Iyer", 3, NcertClass.CLASS_10, 3100, 18, "Stellar Scholar", true, "Reviewing Light Ray Diagrams"),
      Friend("f3", "Kabir Mehta", 4, NcertClass.CLASS_10, 1890, 5, "Orbital Pioneer", false, "Offline 2h ago"),
      Friend("f4", "Sneha Roy", 5, NcertClass.CLASS_10, 4200, 24, "Cosmic Master", true, "Testing AI Study DNA"),
      Friend("f5", "Dev Patel", 6, NcertClass.CLASS_10, 1120, 3, "Orbital Pioneer", false, "Offline 1d ago")
    )
  )
  val friends: StateFlow<List<Friend>> = _friends.asStateFlow()

  private val _chatMessages = MutableStateFlow(
    listOf(
      StudyChatMessage("c1", "Ananya Iyer", false, "Have you reviewed the ray diagram for concave mirror when object is at center of curvature (C)?", null, "10:14 AM"),
      StudyChatMessage("c2", "Class Peer", true, "Yes! The image is also formed at C: real, inverted, and equal in size to the object.", "1/f = 1/v + 1/u", "10:16 AM"),
      StudyChatMessage("c3", "Ananya Iyer", false, "Exactly, with linear magnification m = -1. Calibrey AI breaks down the proof step-by-step.", null, "10:18 AM")
    )
  )
  val chatMessages: StateFlow<List<StudyChatMessage>> = _chatMessages.asStateFlow()

  // User Actions
  suspend fun authenticateUser(name: String, role: UserRole, ncertClass: NcertClass, avatarId: Int, apiKey: String) {
    dao.saveUserProfile(
      UserProfileEntity(
        id = "primary_user",
        fullName = name,
        role = role.name,
        ncertClass = ncertClass.name,
        avatarId = avatarId,
        geminiApiKey = apiKey,
        gravityPoints = 100, // Welcome grant for setting up academic profile
        currentStreak = 1,
        bestStreak = 1,
        totalMinutesStudied = 0,
        isAuthenticated = true
      )
    )
  }

  suspend fun signOut() {
    dao.clearUserProfile()
  }

  suspend fun updateUserProfile(name: String, role: UserRole, ncertClass: NcertClass, avatarId: Int, apiKey: String) {
    val current = userProfile.value
    dao.saveUserProfile(
      UserProfileEntity(
        id = "primary_user",
        fullName = name,
        role = role.name,
        ncertClass = ncertClass.name,
        avatarId = avatarId,
        geminiApiKey = apiKey,
        gravityPoints = current.gravityPoints,
        currentStreak = current.currentStreak,
        bestStreak = current.bestStreak,
        totalMinutesStudied = current.totalMinutesStudied,
        isAuthenticated = true
      )
    )
  }

  suspend fun updateApiKey(apiKey: String) {
    dao.updateApiKey(apiKey)
  }

  suspend fun updateClass(newClass: NcertClass) {
    dao.updateUserClass(newClass.name)
  }

  suspend fun addGravityPoints(amount: Int) {
    dao.addGravityPoints(amount)
  }

  suspend fun toggleChapterBookmark(chapterId: String, currentVal: Boolean) {
    val existing = dao.getChapterProgress(chapterId)
    val updated = ChapterProgressEntity(
      chapterId = chapterId,
      isCompleted = existing?.isCompleted ?: false,
      isBookmarked = !currentVal,
      progressPercent = existing?.progressPercent ?: 0f,
      lastStudiedTimestamp = System.currentTimeMillis()
    )
    dao.saveChapterProgress(updated)
  }

  suspend fun toggleSubtopicCompletion(subtopicId: String, chapterId: String, currentStatus: Boolean) {
    dao.saveSubtopicProgress(
      SubtopicProgressEntity(
        subtopicId = subtopicId,
        chapterId = chapterId,
        isCompleted = !currentStatus,
        isBookmarked = false
      )
    )
    // Check and update chapter progress percentage
    val chapter = NcertCurriculumData.class10ScienceChapters.find { it.id == chapterId }
      ?: NcertCurriculumData.class10MathChapters.find { it.id == chapterId }
    if (chapter != null) {
      val total = chapter.subtopics.size.coerceAtLeast(1)
      val currentProgress = dao.getChapterProgress(chapterId)
      val newPercent = if (!currentStatus) 1.0f else 0.5f
      dao.saveChapterProgress(
        ChapterProgressEntity(
          chapterId = chapterId,
          isCompleted = newPercent >= 1.0f,
          isBookmarked = currentProgress?.isBookmarked ?: false,
          progressPercent = newPercent,
          lastStudiedTimestamp = System.currentTimeMillis()
        )
      )
      if (!currentStatus) {
        dao.addGravityPoints(50)
      }
    }
  }

  suspend fun recordQuizCompletion(quizId: String, chapterId: String, chapterTitle: String, score: Int, total: Int, rewardGp: Int) {
    dao.recordQuizAttempt(
      QuizHistoryEntity(
        quizId = quizId,
        chapterId = chapterId,
        chapterTitle = chapterTitle,
        score = score,
        totalQuestions = total,
        earnedGp = rewardGp,
        timestamp = System.currentTimeMillis()
      )
    )
    dao.addGravityPoints(rewardGp)

    // Mark daily mission progress
    _dailyMissions.update { list ->
      list.map {
        if (it.type == MissionType.QUIZ && !it.isCompleted) {
          it.copy(current = it.current + 1, isCompleted = true)
        } else it
      }
    }

    // Log activity
    val today = dateFormat.format(Date())
    dao.logActivity(
      ActivityLogEntity(
        date = today,
        minutesStudied = 20,
        quizzesTaken = 1,
        chaptersRevised = 1,
        intensity = 3
      )
    )
  }

  suspend fun saveRevisionNote(chapterId: String, chapterTitle: String, subjectName: String, type: NoteType, content: String) {
    dao.saveNote(
      SavedNoteEntity(
        id = UUID.randomUUID().toString(),
        chapterId = chapterId,
        chapterTitle = chapterTitle,
        subjectName = subjectName,
        noteType = type.name,
        content = content,
        createdAt = System.currentTimeMillis()
      )
    )
    dao.addGravityPoints(30)
  }

  suspend fun deleteNote(id: String) {
    dao.deleteNote(id)
  }

  fun sendChatMessage(text: String, formula: String? = null) {
    val newMsg = StudyChatMessage(
      id = UUID.randomUUID().toString(),
      senderName = userProfile.value.fullName,
      isFromUser = true,
      messageText = text,
      formulaSnippet = formula,
      timestamp = SimpleDateFormat("h:mm a", Locale.getDefault()).format(Date())
    )
    _chatMessages.update { it + newMsg }
  }

  private fun generateInitialActivityLogs(): List<HeatmapDay> {
    val list = mutableListOf<HeatmapDay>()
    val cal = Calendar.getInstance()
    cal.add(Calendar.DAY_OF_YEAR, -56) // 8 weeks of history
    val now = Calendar.getInstance()

    val random = Random(42)
    while (cal.before(now) || cal.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)) {
      val d = dateFormat.format(cal.time)
      val intensity = if (random.nextFloat() > 0.25f) (1..4).random(random) else 0
      val minutes = intensity * 25
      val quizzes = if (intensity > 2) 2 else (if (intensity > 0) 1 else 0)
      list.add(HeatmapDay(d, intensity, minutes, quizzes, "Day"))
      cal.add(Calendar.DAY_OF_YEAR, 1)
    }
    return list
  }
}
