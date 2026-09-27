package com.example.model

data class Friend(
  val id: String,
  val name: String,
  val avatarId: Int,
  val ncertClass: NcertClass,
  val gravityPoints: Int,
  val currentStreak: Int,
  val rankTitle: String,
  val isOnline: Boolean,
  val currentActivity: String,
  val friendshipStatus: FriendshipStatus = FriendshipStatus.ACCEPTED
)

enum class FriendshipStatus {
  ACCEPTED,
  PENDING_INCOMING,
  PENDING_OUTGOING
}

data class StudyChatMessage(
  val id: String,
  val senderName: String,
  val isFromUser: Boolean,
  val messageText: String,
  val formulaSnippet: String? = null,
  val timestamp: String
)

data class LeaderboardEntry(
  val rank: Int,
  val name: String,
  val avatarId: Int,
  val ncertClass: NcertClass,
  val gravityPoints: Int,
  val streak: Int,
  val isCurrentUser: Boolean = false
)
