package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ai.AiMessage
import com.example.ai.AiTutorMode
import com.example.ai.CalibreyAiService
import com.example.data.CalibreyRepository
import com.example.data.curriculum.NcertCurriculumData
import com.example.model.*
import com.example.ui.components.NavigationTab
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface ScreenDestination {
  object MainTabs : ScreenDestination
  data class ChapterDetail(val chapter: Chapter) : ScreenDestination
  data class Quiz(val quizSet: QuizSet) : ScreenDestination
  object KnowledgeGraph : ScreenDestination
  object StudyDna : ScreenDestination
  object ExamWarRoom : ScreenDestination
  data class AiRevisionNotes(val chapterTitle: String?) : ScreenDestination
}

class CalibreyViewModel(
  private val repository: CalibreyRepository,
  private val aiService: CalibreyAiService = CalibreyAiService()
) : ViewModel() {

  // Flow states from repository
  val userProfile = repository.userProfile
  val chapterProgressMap = repository.chapterProgressMap
  val subtopicProgressMap = repository.subtopicProgressMap
  val quizHistory = repository.quizHistory
  val savedNotes = repository.savedNotes
  val activityLogs = repository.activityLogs
  val dailyMissions = repository.dailyMissions
  val friends = repository.friends
  val chatMessages = repository.chatMessages

  // App UI Navigation States
  var isIntroActive = MutableStateFlow(true)
    private set

  var currentTab = MutableStateFlow(NavigationTab.DASHBOARD)
    private set

  var activeDestination = MutableStateFlow<ScreenDestination>(ScreenDestination.MainTabs)
    private set

  var isProfileDialogOpen = MutableStateFlow(false)
    private set

  // AI Tutor State
  private val _aiMessages = MutableStateFlow(
    listOf(
      AiMessage(
        id = "welcome_ai",
        isUser = false,
        content = "Greetings! I am Calibrey AI, your Socratic NCERT tutor. I can break down challenging concepts, guide you through step-by-step mathematical proofs, conduct board exam drills, and analyze your weak topics. What would you like to master today?"
      )
    )
  )
  val aiMessages: StateFlow<List<AiMessage>> = _aiMessages.asStateFlow()

  var isAiThinking = MutableStateFlow(false)
    private set

  // AI Revision Notes Generator State
  var generatedNoteContent = MutableStateFlow<String?>(null)
    private set

  var isGeneratingNote = MutableStateFlow(false)
    private set

  fun finishIntro() {
    isIntroActive.value = false
  }

  fun selectTab(tab: NavigationTab) {
    currentTab.value = tab
    activeDestination.value = ScreenDestination.MainTabs
  }

  fun navigateToDestination(destination: ScreenDestination) {
    activeDestination.value = destination
  }

  fun navigateBack() {
    activeDestination.value = ScreenDestination.MainTabs
  }

  fun openProfileDialog() {
    isProfileDialogOpen.value = true
  }

  fun closeProfileDialog() {
    isProfileDialogOpen.value = false
  }

  fun updateProfile(name: String, role: UserRole, ncertClass: NcertClass, avatarId: Int, apiKey: String) {
    viewModelScope.launch {
      repository.updateUserProfile(name, role, ncertClass, avatarId, apiKey)
    }
  }

  fun toggleChapterBookmark(chapterId: String, currentVal: Boolean) {
    viewModelScope.launch {
      repository.toggleChapterBookmark(chapterId, currentVal)
    }
  }

  fun toggleSubtopic(subtopicId: String, chapterId: String, currentStatus: Boolean) {
    viewModelScope.launch {
      repository.toggleSubtopicCompletion(subtopicId, chapterId, currentStatus)
    }
  }

  fun onQuizFinished(score: Int, total: Int, rewardGp: Int, quizSet: QuizSet) {
    viewModelScope.launch {
      repository.recordQuizCompletion(
        quizId = quizSet.id,
        chapterId = quizSet.chapterId,
        chapterTitle = quizSet.chapterTitle,
        score = score,
        total = total,
        rewardGp = rewardGp
      )
      activeDestination.value = ScreenDestination.MainTabs
    }
  }

  fun claimMission(mission: DailyMission) {
    viewModelScope.launch {
      repository.addGravityPoints(mission.rewardGp)
    }
  }

  fun sendPeerMessage(text: String) {
    repository.sendChatMessage(text)
  }

  fun askAiTutor(query: String, mode: AiTutorMode, currentChapter: String? = null) {
    val userMsg = AiMessage(id = System.currentTimeMillis().toString(), isUser = true, content = query)
    _aiMessages.update { it + userMsg }
    isAiThinking.value = true

    viewModelScope.launch {
      val profile = userProfile.value
      val response = aiService.askTutor(
        userQuery = query,
        mode = mode,
        ncertClass = profile.ncertClass,
        subjectName = "Science",
        currentChapter = currentChapter,
        userApiKey = profile.geminiApiKey
      )
      isAiThinking.value = false
      _aiMessages.update {
        it + AiMessage(id = (System.currentTimeMillis() + 1).toString(), isUser = false, content = response)
      }
    }
  }

  fun generateRevisionNote(chapterTitle: String, noteType: NoteType) {
    isGeneratingNote.value = true
    generatedNoteContent.value = null
    viewModelScope.launch {
      val profile = userProfile.value
      val content = aiService.generateRevisionNotes(
        chapterTitle = chapterTitle,
        subjectName = "NCERT Curriculum",
        noteType = noteType,
        userApiKey = profile.geminiApiKey
      )
      isGeneratingNote.value = false
      generatedNoteContent.value = content
    }
  }

  fun saveRevisionNote(chapterTitle: String, noteType: NoteType, content: String) {
    viewModelScope.launch {
      repository.saveRevisionNote(
        chapterId = "note_${System.currentTimeMillis()}",
        chapterTitle = chapterTitle,
        subjectName = "Science",
        type = noteType,
        content = content
      )
    }
  }

  fun deleteSavedNote(id: String) {
    viewModelScope.launch {
      repository.deleteNote(id)
    }
  }
}
