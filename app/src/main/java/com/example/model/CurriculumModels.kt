package com.example.model

data class Subject(
  val id: String,
  val name: String,
  val code: String,
  val iconName: String,
  val ncertClass: NcertClass,
  val chaptersCount: Int,
  val colorHex: Long = 0xFFFFFFFF
)

enum class ChapterDifficulty {
  FOUNDATIONAL,
  CORE,
  ADVANCED
}

data class Chapter(
  val id: String,
  val subjectId: String,
  val ncertClass: NcertClass,
  val chapterNumber: Int,
  val title: String,
  val subtitle: String,
  val summary: String,
  val estimatedMinutes: Int,
  val difficulty: ChapterDifficulty,
  val youtubeVideoId: String,
  val videoTitle: String,
  val videoDuration: String,
  val formulas: List<String>,
  val keyTakeaways: List<String>,
  val subtopics: List<Subtopic>,
  val isCompleted: Boolean = false,
  val isBookmarked: Boolean = false,
  val progressPercent: Float = 0f
)

data class Subtopic(
  val id: String,
  val chapterId: String,
  val subtopicNumber: String,
  val title: String,
  val summary: String,
  val contentMarkdown: String,
  val youtubeVideoId: String = "",
  val isCompleted: Boolean = false,
  val isBookmarked: Boolean = false
)

data class QuizQuestion(
  val id: String,
  val questionText: String,
  val options: List<String>,
  val correctOptionIndex: Int,
  val explanation: String,
  val hint: String,
  val conceptTag: String
)

data class QuizSet(
  val id: String,
  val chapterId: String,
  val chapterTitle: String,
  val title: String,
  val difficulty: ChapterDifficulty,
  val questions: List<QuizQuestion>,
  val rewardGp: Int = 150
)

data class QuizAttempt(
  val id: String,
  val quizId: String,
  val chapterId: String,
  val chapterTitle: String,
  val score: Int,
  val totalQuestions: Int,
  val earnedGp: Int,
  val timestamp: Long = System.currentTimeMillis()
) {
  val percentage: Int get() = if (totalQuestions > 0) (score * 100) / totalQuestions else 0
}
