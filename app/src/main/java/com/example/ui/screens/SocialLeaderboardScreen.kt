package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

enum class SocialSubTab(val label: String) {
  LEADERBOARD("Leaderboard"),
  STUDY_BUDDIES("Study Buddies"),
  TEACHER_OVERSIGHT("Teacher Oversight")
}

@Composable
fun SocialLeaderboardScreen(
  userProfile: UserProfile,
  friends: List<Friend>,
  chatMessages: List<StudyChatMessage>,
  onSendMessage: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedTab by remember { mutableStateOf(SocialSubTab.LEADERBOARD) }
  var chatInput by remember { mutableStateOf("") }

  val leaderboards = remember(userProfile.gravityPoints) {
    listOf(
      LeaderboardEntry(1, "Sneha Roy", 5, NcertClass.CLASS_10, 4200, 24),
      LeaderboardEntry(2, "Ananya Iyer", 3, NcertClass.CLASS_10, 3100, 18),
      LeaderboardEntry(3, "Rohan Verma", 2, NcertClass.CLASS_10, 2340, 12),
      LeaderboardEntry(4, userProfile.fullName, userProfile.avatarId, userProfile.ncertClass, userProfile.gravityPoints, userProfile.currentStreak, isCurrentUser = true),
      LeaderboardEntry(5, "Kabir Mehta", 4, NcertClass.CLASS_10, 1890, 5),
      LeaderboardEntry(6, "Dev Patel", 6, NcertClass.CLASS_10, 1120, 3)
    ).sortedByDescending { it.gravityPoints }.mapIndexed { index, item -> item.copy(rank = index + 1) }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Top Tabs
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(SocialSubTab.values()) { tab ->
        val isSelected = selectedTab == tab
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.08f))
            .clickable { selectedTab = tab }
            .padding(horizontal = 14.dp, vertical = 7.dp)
        ) {
          Text(
            text = tab.label,
            color = if (isSelected) ObsidianVoid else PlatinumWhite,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    when (selectedTab) {
      SocialSubTab.LEADERBOARD -> {
        // Leaderboard List
        LazyColumn(
          verticalArrangement = Arrangement.spacedBy(8.dp),
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(bottom = 80.dp)
        ) {
          item {
            Text(
              text = "GLOBAL NCERT SPRINT RANKINGS",
              color = SilverMedium,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
          }

          items(leaderboards) { entry ->
            val isUser = entry.isCurrentUser
            val rankBadge = when (entry.rank) {
              1 -> "🥇"
              2 -> "🥈"
              3 -> "🥉"
              else -> "#${entry.rank}"
            }

            GlassCard(
              modifier = Modifier.fillMaxWidth(),
              backgroundAlpha = if (isUser) 0.16f else 0.05f,
              borderAlpha = if (isUser) 0.4f else 0.15f
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(
                    text = rankBadge,
                    fontSize = if (entry.rank <= 3) 18.sp else 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = PlatinumWhite,
                    modifier = Modifier.width(32.dp)
                  )

                  Box(
                    modifier = Modifier
                      .size(36.dp)
                      .clip(CircleShape)
                      .background(Color.White.copy(alpha = 0.1f)),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(text = when (entry.avatarId) {
                      1 -> "👨‍🎓"
                      2 -> "👩‍🎓"
                      3 -> "🧑‍🔬"
                      4 -> "👩‍💻"
                      5 -> "🧑‍🏫"
                      else -> "🚀"
                    }, fontSize = 18.sp)
                  }

                  Spacer(modifier = Modifier.width(10.dp))

                  Column {
                    Text(
                      text = entry.name + if (isUser) " (You)" else "",
                      color = PlatinumWhite,
                      fontSize = 14.sp,
                      fontWeight = if (isUser) FontWeight.Bold else FontWeight.Medium
                    )
                    Text(
                      text = "🔥 ${entry.streak} day streak",
                      color = SilverMedium,
                      fontSize = 11.sp
                    )
                  }
                }

                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(GravityAmber.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Text(
                    text = "${entry.gravityPoints} GP",
                    color = GravityAmber,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }
            }
          }
        }
      }

      SocialSubTab.STUDY_BUDDIES -> {
        // Classmates & Direct Study Chat
        Column(modifier = Modifier.fillMaxSize()) {
          Text(
            text = "CLASSROOM PEERS",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(6.dp))

          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(friends) { friend ->
              Column(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color.White.copy(alpha = 0.05f))
                  .border(0.5.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                  .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Box(
                  modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.1f)),
                  contentAlignment = Alignment.Center
                ) {
                  Text("👤", fontSize = 16.sp)
                  if (friend.isOnline) {
                    Box(
                      modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(MasteryEmerald)
                        .align(Alignment.BottomEnd)
                    )
                  }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = friend.name.split(" ").first(), color = PlatinumWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text(text = "${friend.gravityPoints} GP", color = GravityAmber, fontSize = 9.sp)
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Study Room Chat
          Text(
            text = "STUDY ROOM DIRECT CHAT",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(6.dp))

          LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(chatMessages) { msg ->
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (msg.isFromUser) Arrangement.End else Arrangement.Start
              ) {
                Column(
                  modifier = Modifier
                    .widthIn(max = 280.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (msg.isFromUser) PlatinumWhite else Color.White.copy(alpha = 0.08f))
                    .padding(10.dp)
                ) {
                  Text(
                    text = msg.senderName,
                    color = if (msg.isFromUser) ObsidianVoid else SilverMedium,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                  )
                  Spacer(modifier = Modifier.height(2.dp))
                  Text(
                    text = msg.messageText,
                    color = if (msg.isFromUser) ObsidianVoid else PlatinumWhite,
                    fontSize = 13.sp
                  )
                  if (msg.formulaSnippet != null) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = "⚡ ${msg.formulaSnippet}",
                      color = if (msg.isFromUser) ObsidianVoid else GravityAmber,
                      fontSize = 11.sp,
                      fontWeight = FontWeight.Bold
                    )
                  }
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(8.dp))

          // Chat Input
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(20.dp))
              .background(ObsidianDark)
              .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(20.dp))
              .padding(horizontal = 12.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            TextField(
              value = chatInput,
              onValueChange = { chatInput = it },
              placeholder = { Text("Share doubt or formula...", color = SilverMuted, fontSize = 12.sp) },
              colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = PlatinumWhite
              ),
              modifier = Modifier.weight(1f)
            )
            IconButton(
              onClick = {
                if (chatInput.isNotBlank()) {
                  onSendMessage(chatInput.trim())
                  chatInput = ""
                }
              }
            ) {
              Icon(Icons.Default.Send, contentDescription = "Send", tint = PlatinumWhite)
            }
          }

          Spacer(modifier = Modifier.height(80.dp))
        }
      }

      SocialSubTab.TEACHER_OVERSIGHT -> {
        // Teacher Command Dashboard View
        LazyColumn(
          verticalArrangement = Arrangement.spacedBy(10.dp),
          modifier = Modifier.fillMaxSize(),
          contentPadding = PaddingValues(bottom = 80.dp)
        ) {
          item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🧑‍🏫", fontSize = 20.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                  Text("TEACHER & MENTOR OVERSIGHT", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                  Text("Class 10 Science Section A", color = PlatinumWhite, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                }
              }
              Spacer(modifier = Modifier.height(10.dp))
              Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column {
                  Text("Active Students", color = SilverMedium, fontSize = 11.sp)
                  Text("34 Enrolled", color = PlatinumWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Column {
                  Text("Class Average GP", color = SilverMedium, fontSize = 11.sp)
                  Text("2,410 GP", color = GravityAmber, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Column {
                  Text("Syllabus Target", color = SilverMedium, fontSize = 11.sp)
                  Text("78% On Track", color = MasteryEmerald, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }

          item {
            Text("CRITICAL WEAK TOPIC ALERTS (NEEDS ATTENTION)", color = UrgentRose, fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }

          item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
              Text("⚠️ 14 Students Struggling with Ray Diagram Conventions in Chapter 10", color = PlatinumWhite, fontSize = 13.sp, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.height(4.dp))
              Text("Recommended Teacher Action: Broadcast an exemplar walkthrough or assign a 5-question Socratic drill.", color = SilverMedium, fontSize = 11.sp)
            }
          }

          item {
            Text("STUDENT ROSTER & SUBMISSION STATUS", color = SilverMedium, fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }

          items(friends) { student ->
            GlassCard(modifier = Modifier.fillMaxWidth()) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Column {
                  Text(text = student.name, color = PlatinumWhite, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                  Text(text = "Current Activity: ${student.currentActivity}", color = SilverMedium, fontSize = 11.sp)
                }
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White.copy(alpha = 0.1f))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                  Text(text = "${student.gravityPoints} GP", color = PlatinumWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        }
      }
    }
  }
}
