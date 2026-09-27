package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.data.CalibreyRepository
import com.example.data.curriculum.NcertCurriculumData
import com.example.data.local.CalibreyDatabase
import com.example.model.UserProfile
import com.example.ui.CalibreyViewModel
import com.example.ui.ScreenDestination
import com.example.ui.components.*
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.ObsidianVoid

class MainActivity : ComponentActivity() {

  private lateinit var viewModel: CalibreyViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    val database = CalibreyDatabase.getDatabase(applicationContext)
    val repository = CalibreyRepository(database.calibreyDao(), lifecycleScope)
    viewModel = CalibreyViewModel(repository)

    setContent {
      MyApplicationTheme {
        CalibreyApp(viewModel = viewModel)
      }
    }
  }
}

@Composable
fun CalibreyApp(viewModel: CalibreyViewModel) {
  val isIntroActive by viewModel.isIntroActive.collectAsStateWithLifecycle()
  val userProfile by viewModel.userProfile.collectAsStateWithLifecycle()
  val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
  val activeDestination by viewModel.activeDestination.collectAsStateWithLifecycle()
  val isProfileDialogOpen by viewModel.isProfileDialogOpen.collectAsStateWithLifecycle()

  val chapterProgressMap by viewModel.chapterProgressMap.collectAsStateWithLifecycle()
  val subtopicProgressMap by viewModel.subtopicProgressMap.collectAsStateWithLifecycle()
  val activityLogs by viewModel.activityLogs.collectAsStateWithLifecycle()
  val dailyMissions by viewModel.dailyMissions.collectAsStateWithLifecycle()
  val friends by viewModel.friends.collectAsStateWithLifecycle()
  val chatMessages by viewModel.chatMessages.collectAsStateWithLifecycle()
  val aiMessages by viewModel.aiMessages.collectAsStateWithLifecycle()
  val isAiThinking by viewModel.isAiThinking.collectAsStateWithLifecycle()
  val savedNotes by viewModel.savedNotes.collectAsStateWithLifecycle()
  val generatedNoteContent by viewModel.generatedNoteContent.collectAsStateWithLifecycle()
  val isGeneratingNote by viewModel.isGeneratingNote.collectAsStateWithLifecycle()

  // Handle hardware / gesture Back button
  BackHandler(enabled = activeDestination !is ScreenDestination.MainTabs) {
    viewModel.navigateBack()
  }

  if (isIntroActive) {
    CinematicIntro(onFinishIntro = { viewModel.finishIntro() })
    return
  }

  Scaffold(
    modifier = Modifier
      .fillMaxSize()
      .background(ObsidianVoid),
    topBar = {
      if (activeDestination is ScreenDestination.MainTabs) {
        GlassTopBar(
          title = currentTab.label,
          gravityPoints = userProfile.gravityPoints,
          streakDays = userProfile.currentStreak,
          avatarId = userProfile.avatarId,
          onProfileClick = { viewModel.openProfileDialog() },
          modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)
        )
      }
    },
    bottomBar = {
      if (activeDestination is ScreenDestination.MainTabs) {
        GlassBottomNavigation(
          currentTab = currentTab,
          onTabSelected = { viewModel.selectTab(it) }
        )
      }
    }
  ) { innerPadding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(ObsidianVoid)
        .padding(innerPadding)
    ) {
      when (val dest = activeDestination) {
        is ScreenDestination.MainTabs -> {
          when (currentTab) {
            NavigationTab.DASHBOARD -> {
              DashboardScreen(
                userProfile = userProfile,
                activityLogs = activityLogs,
                dailyMissions = dailyMissions,
                onNavigateToCurriculum = { viewModel.selectTab(NavigationTab.LEARN) },
                onNavigateToChapter = { viewModel.navigateToDestination(ScreenDestination.ChapterDetail(it)) },
                onNavigateToKnowledgeGraph = { viewModel.navigateToDestination(ScreenDestination.KnowledgeGraph) },
                onNavigateToExamWarRoom = { viewModel.navigateToDestination(ScreenDestination.ExamWarRoom) },
                onNavigateToStudyDna = { viewModel.navigateToDestination(ScreenDestination.StudyDna) },
                onNavigateToAiTutor = { viewModel.selectTab(NavigationTab.BRAIN) },
                onNavigateToRevision = { viewModel.selectTab(NavigationTab.REVISE) },
                onClaimMission = { viewModel.claimMission(it) }
              )
            }
            NavigationTab.LEARN -> {
              CurriculumScreen(
                ncertClass = userProfile.ncertClass,
                chapterProgressMap = chapterProgressMap,
                onChapterSelected = { viewModel.navigateToDestination(ScreenDestination.ChapterDetail(it)) },
                onToggleBookmark = { id, current -> viewModel.toggleChapterBookmark(id, current) }
              )
            }
            NavigationTab.BRAIN -> {
              AiTutorScreen(
                userProfile = userProfile,
                messages = aiMessages,
                isLoading = isAiThinking,
                onSendMessage = { query, mode -> viewModel.askAiTutor(query, mode) }
              )
            }
            NavigationTab.REVISE -> {
              RevisionCenterScreen(
                ncertClass = userProfile.ncertClass,
                onNavigateToKnowledgeGraph = { viewModel.navigateToDestination(ScreenDestination.KnowledgeGraph) },
                onNavigateToWarRoom = { viewModel.navigateToDestination(ScreenDestination.ExamWarRoom) },
                onNavigateToStudyDna = { viewModel.navigateToDestination(ScreenDestination.StudyDna) },
                onNavigateToSavedNotes = { viewModel.navigateToDestination(ScreenDestination.AiRevisionNotes(null)) },
                onStartRevisionDrill = { chapterId ->
                  val quizzes = NcertCurriculumData.getQuizzesForChapter(chapterId)
                  quizzes.firstOrNull()?.let {
                    viewModel.navigateToDestination(ScreenDestination.Quiz(it))
                  }
                }
              )
            }
            NavigationTab.SOCIAL -> {
              SocialLeaderboardScreen(
                userProfile = userProfile,
                friends = friends,
                chatMessages = chatMessages,
                onSendMessage = { viewModel.sendPeerMessage(it) }
              )
            }
          }
        }

        is ScreenDestination.ChapterDetail -> {
          ChapterDetailScreen(
            chapter = dest.chapter,
            subtopicProgressMap = subtopicProgressMap,
            onBackClick = { viewModel.navigateBack() },
            onTakeQuiz = {
              val quizzes = NcertCurriculumData.getQuizzesForChapter(dest.chapter.id)
              val targetQuiz = quizzes.firstOrNull()
              if (targetQuiz != null) {
                viewModel.navigateToDestination(ScreenDestination.Quiz(targetQuiz))
              }
            },
            onGenerateNotes = {
              viewModel.navigateToDestination(ScreenDestination.AiRevisionNotes(dest.chapter.title))
            },
            onAskAi = {
              viewModel.selectTab(NavigationTab.BRAIN)
            },
            onToggleSubtopic = { subtopicId, current ->
              viewModel.toggleSubtopic(subtopicId, dest.chapter.id, current)
            }
          )
        }

        is ScreenDestination.Quiz -> {
          QuizScreen(
            quizSet = dest.quizSet,
            onQuizFinished = { score, total, rewardGp ->
              viewModel.onQuizFinished(score, total, rewardGp, dest.quizSet)
            },
            onBackClick = { viewModel.navigateBack() },
            onAskAiDoubt = { question ->
              viewModel.askAiTutor("Explain NCERT solution and hint for: '${question.questionText}'", com.example.ai.AiTutorMode.DOUBT_SOLVER)
              viewModel.selectTab(NavigationTab.BRAIN)
            }
          )
        }

        is ScreenDestination.KnowledgeGraph -> {
          KnowledgeGraphScreen(
            ncertClass = userProfile.ncertClass,
            onBackClick = { viewModel.navigateBack() },
            onNodeSelected = { node ->
              val chapter = NcertCurriculumData.class10ScienceChapters.find { it.id == node.chapterId }
                ?: NcertCurriculumData.class10MathChapters.find { it.id == node.chapterId }
              if (chapter != null) {
                viewModel.navigateToDestination(ScreenDestination.ChapterDetail(chapter))
              }
            }
          )
        }

        is ScreenDestination.StudyDna -> {
          StudyDnaScreen(
            userProfile = userProfile,
            onBackClick = { viewModel.navigateBack() }
          )
        }

        is ScreenDestination.ExamWarRoom -> {
          ExamWarRoomScreen(
            ncertClass = userProfile.ncertClass,
            onBackClick = { viewModel.navigateBack() },
            onStartExamSprint = {
              val quizzes = NcertCurriculumData.getQuizzesForChapter("c10_sci_ch1")
              quizzes.firstOrNull()?.let {
                viewModel.navigateToDestination(ScreenDestination.Quiz(it))
              }
            }
          )
        }

        is ScreenDestination.AiRevisionNotes -> {
          AiRevisionNotesScreen(
            ncertClass = userProfile.ncertClass,
            initialChapterTitle = dest.chapterTitle,
            savedNotes = savedNotes,
            onBackClick = { viewModel.navigateBack() },
            onGenerateNotes = { chTitle, type -> viewModel.generateRevisionNote(chTitle, type) },
            onSaveNote = { chTitle, type, content -> viewModel.saveRevisionNote(chTitle, type, content) },
            onDeleteNote = { id -> viewModel.deleteSavedNote(id) },
            generatedContent = generatedNoteContent,
            isGenerating = isGeneratingNote
          )
        }
      }
    }

    if (isProfileDialogOpen) {
      ProfileDialog(
        userProfile = userProfile,
        onDismiss = { viewModel.closeProfileDialog() },
        onSaveProfile = { name, role, ncertClass, avatarId, apiKey ->
          viewModel.updateProfile(name, role, ncertClass, avatarId, apiKey)
        }
      )
    }
  }
}
