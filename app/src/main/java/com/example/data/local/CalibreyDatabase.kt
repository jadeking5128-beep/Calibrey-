package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
  entities = [
    UserProfileEntity::class,
    ChapterProgressEntity::class,
    SubtopicProgressEntity::class,
    QuizHistoryEntity::class,
    SavedNoteEntity::class,
    ActivityLogEntity::class
  ],
  version = 2,
  exportSchema = false
)
abstract class CalibreyDatabase : RoomDatabase() {
  abstract fun calibreyDao(): CalibreyDao

  companion object {
    @Volatile
    private var INSTANCE: CalibreyDatabase? = null

    fun getDatabase(context: Context): CalibreyDatabase {
      return INSTANCE ?: synchronized(this) {
        val instance = Room.databaseBuilder(
          context.applicationContext,
          CalibreyDatabase::class.java,
          "calibrey_os.db"
        )
        .fallbackToDestructiveMigration(dropAllTables = true)
        .build()
        INSTANCE = instance
        instance
      }
    }
  }
}
