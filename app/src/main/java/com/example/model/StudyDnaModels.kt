package com.example.model

enum class RevisionUrgency {
  URGENT,       // High decay / overdue / low score
  RECOMMENDED,  // Approaching interval
  MASTERED      // Fresh / score >= 90%
}

data class RevisionItem(
  val id: String,
  val chapterId: String,
  val chapterTitle: String,
  val subjectName: String,
  val lastQuizScore: Int,
  val lastReviewedTimestamp: Long,
  val urgency: RevisionUrgency,
  val decayRate: Float, // 0.0 to 1.0
  val weakConcepts: List<String>
)

data class StudyDna(
  val studentName: String,
  val ncertClass: NcertClass,
  val learningVelocity: Float, // e.g. 8.4 / 10
  val retentionIndex: Int,      // e.g. 88%
  val consistencyScore: Int,    // e.g. 94%
  val strengths: List<String>,
  val weaknesses: List<String>,
  val recommendedTactics: List<String>,
  val problemAreas: List<String>,
  val predictedBoardScore: Int  // e.g. 93%
)

data class HeatmapDay(
  val date: String, // "YYYY-MM-DD"
  val intensity: Int, // 0 to 4
  val minutesStudied: Int,
  val quizzesCompleted: Int,
  val dayOfWeek: String
)

enum class MissionType {
  QUIZ,
  REVISE,
  AI_TUTOR,
  STUDY_TIME
}

data class DailyMission(
  val id: String,
  val title: String,
  val description: String,
  val target: Int,
  val current: Int,
  val rewardGp: Int,
  val isCompleted: Boolean = false,
  val type: MissionType
) {
  val progressFraction: Float get() = (current.toFloat() / target.coerceAtLeast(1)).coerceIn(0f, 1f)
}

enum class NodeStatus {
  MASTERED,
  IN_PROGRESS,
  REVISION_NEEDED,
  WEAK_AREA,
  LOCKED
}

data class KnowledgeNode(
  val id: String,
  val chapterId: String,
  val title: String,
  val subjectId: String,
  val masteryPercent: Int,
  val status: NodeStatus,
  val connectedNodeIds: List<String> = emptyList(),
  val keyFormula: String = ""
)

enum class NoteType(val label: String) {
  SUMMARY("NCERT Summary"),
  FORMULAS("Formula Sheet"),
  FLASHCARDS("Flashcards"),
  BOARD_QUESTIONS("Top 10 Board Questions"),
  COMMON_MISTAKES("Common Traps & Mistakes")
}

data class SavedNote(
  val id: String,
  val chapterId: String,
  val chapterTitle: String,
  val subjectName: String,
  val noteType: NoteType,
  val content: String,
  val createdAt: Long = System.currentTimeMillis()
)

data class Achievement(
  val id: String,
  val title: String,
  val description: String,
  val iconEmoji: String,
  val isUnlocked: Boolean,
  val unlockedDate: String = "",
  val rewardGp: Int
)
