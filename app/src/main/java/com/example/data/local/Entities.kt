package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class UserProfileEntity(
  @PrimaryKey val id: String = "primary_user",
  val fullName: String,
  val role: String,
  val ncertClass: String,
  val avatarId: Int,
  val geminiApiKey: String,
  val gravityPoints: Int,
  val currentStreak: Int,
  val bestStreak: Int,
  val totalMinutesStudied: Int,
  val isAuthenticated: Boolean = true
)

@Entity(tableName = "chapter_progress")
data class ChapterProgressEntity(
  @PrimaryKey val chapterId: String,
  val isCompleted: Boolean,
  val isBookmarked: Boolean,
  val progressPercent: Float,
  val lastStudiedTimestamp: Long
)

@Entity(tableName = "subtopic_progress")
data class SubtopicProgressEntity(
  @PrimaryKey val subtopicId: String,
  val chapterId: String,
  val isCompleted: Boolean,
  val isBookmarked: Boolean
)

@Entity(tableName = "quiz_history")
data class QuizHistoryEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  val quizId: String,
  val chapterId: String,
  val chapterTitle: String,
  val score: Int,
  val totalQuestions: Int,
  val earnedGp: Int,
  val timestamp: Long
)

@Entity(tableName = "saved_notes")
data class SavedNoteEntity(
  @PrimaryKey val id: String,
  val chapterId: String,
  val chapterTitle: String,
  val subjectName: String,
  val noteType: String,
  val content: String,
  val createdAt: Long
)

@Entity(tableName = "activity_logs")
data class ActivityLogEntity(
  @PrimaryKey val date: String, // "YYYY-MM-DD"
  val minutesStudied: Int,
  val quizzesTaken: Int,
  val chaptersRevised: Int,
  val intensity: Int
)
