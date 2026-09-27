package com.example.model

enum class UserRole {
  STUDENT,
  TEACHER
}

enum class NcertClass(val displayName: String, val levelNumber: Int) {
  CLASS_9("Class 9", 9),
  CLASS_10("Class 10", 10),
  CLASS_11("Class 11 (Science)", 11),
  CLASS_12("Class 12 (Science)", 12)
}

data class UserProfile(
  val id: String = "primary_user",
  val fullName: String = "",
  val role: UserRole = UserRole.STUDENT,
  val ncertClass: NcertClass = NcertClass.CLASS_10,
  val avatarId: Int = 1,
  val geminiApiKey: String = "",
  val gravityPoints: Int = 0,
  val currentStreak: Int = 0,
  val bestStreak: Int = 0,
  val level: Int = 1,
  val rankTitle: String = "Stardust Scout",
  val totalQuizzesTaken: Int = 0,
  val totalChaptersCompleted: Int = 0,
  val totalMinutesStudied: Int = 0,
  val isAuthenticated: Boolean = false
) {
  val levelProgress: Float
    get() {
      val currentLevelBase = (level - 1) * 500
      val pointsInLevel = (gravityPoints - currentLevelBase).coerceAtLeast(0)
      return (pointsInLevel.toFloat() / 500f).coerceIn(0f, 1f)
    }

  companion object {
    fun calculateRank(points: Int): String {
      return when {
        points >= 10000 -> "Singularity Sovereign"
        points >= 5000 -> "Cosmic Master"
        points >= 2500 -> "Stellar Scholar"
        points >= 1000 -> "Orbital Pioneer"
        else -> "Stardust Scout"
      }
    }

    fun calculateLevel(points: Int): Int {
      return (points / 500) + 1
    }
  }
}
