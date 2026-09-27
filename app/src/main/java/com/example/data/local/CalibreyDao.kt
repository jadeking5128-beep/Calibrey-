package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CalibreyDao {
  // User Profile
  @Query("SELECT * FROM user_profile WHERE id = 'primary_user' LIMIT 1")
  fun getUserProfile(): Flow<UserProfileEntity?>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveUserProfile(user: UserProfileEntity)

  @Query("UPDATE user_profile SET gravityPoints = gravityPoints + :deltaGp WHERE id = 'primary_user'")
  suspend fun addGravityPoints(deltaGp: Int)

  @Query("UPDATE user_profile SET ncertClass = :newClass WHERE id = 'primary_user'")
  suspend fun updateUserClass(newClass: String)

  @Query("UPDATE user_profile SET geminiApiKey = :newKey WHERE id = 'primary_user'")
  suspend fun updateApiKey(newKey: String)

  // Chapter Progress
  @Query("SELECT * FROM chapter_progress")
  fun getAllChapterProgress(): Flow<List<ChapterProgressEntity>>

  @Query("SELECT * FROM chapter_progress WHERE chapterId = :chapterId LIMIT 1")
  suspend fun getChapterProgress(chapterId: String): ChapterProgressEntity?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveChapterProgress(progress: ChapterProgressEntity)

  // Subtopic Progress
  @Query("SELECT * FROM subtopic_progress")
  fun getAllSubtopicProgress(): Flow<List<SubtopicProgressEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveSubtopicProgress(progress: SubtopicProgressEntity)

  // Quiz History
  @Query("SELECT * FROM quiz_history ORDER BY timestamp DESC")
  fun getAllQuizHistory(): Flow<List<QuizHistoryEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun recordQuizAttempt(attempt: QuizHistoryEntity)

  // Saved Revision Notes
  @Query("SELECT * FROM saved_notes ORDER BY createdAt DESC")
  fun getAllSavedNotes(): Flow<List<SavedNoteEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun saveNote(note: SavedNoteEntity)

  @Query("DELETE FROM saved_notes WHERE id = :id")
  suspend fun deleteNote(id: String)

  // Activity Logs
  @Query("SELECT * FROM activity_logs ORDER BY date DESC LIMIT 90")
  fun getRecentActivity(): Flow<List<ActivityLogEntity>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun logActivity(activity: ActivityLogEntity)
}
