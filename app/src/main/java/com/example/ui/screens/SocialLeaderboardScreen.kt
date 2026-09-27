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
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.*
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.components.GlassSegmentedControl
import com.example.ui.components.GlassStatusPill
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
  var selectedTabIndex by remember { mutableStateOf(0) }
  val tabs = SocialSubTab.values()
  val selectedTab = tabs[selectedTabIndex]
  var chatInput by remember { mutableStateOf("") }

  val leaderboards = remember(userProfile.gravityPoints, userProfile.fullName) {
    listOf(
      LeaderboardEntry(1, "Sneha Roy", 1, NcertClass.CLASS_10, 4200, 24),
      LeaderboardEntry(2, "Ananya Iyer", 2, NcertClass.CLASS_10, 3100, 18),
      LeaderboardEntry(3, "Rohan Verma", 3, NcertClass.CLASS_10, 2340, 12),
      LeaderboardEntry(4, userProfile.fullName, userProfile.avatarId, userProfile.ncertClass, userProfile.gravityPoints, userProfile.currentStreak, isCurrentUser = true),
      LeaderboardEntry(5, "Kabir Mehta", 4, NcertClass.CLASS_10, 1890, 5),
      LeaderboardEntry(6, "Dev Patel", 5, NcertClass.CLASS_10, 1120, 3)
    ).sortedByDescending { it.gravityPoints }.mapIndexed { index, item -> item.copy(rank = index + 1) }
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianPure)
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Segmented Navigation Control
    GlassSegmentedControl(
      options = tabs.map { it.label },
      selectedIndex = selectedTabIndex,
      onSelect = { selectedTabIndex = it }
    )

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
              text = "GLOBAL NCERT ACADEMIC RANKINGS",
              color = SilverMedium,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
          }

          items(leaderboards) { entry ->
            val isUser = entry.isCurrentUser
            val rankString = if (entry.rank < 10) "#0${entry.rank}" else "#${entry.rank}"
            val initials = entry.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")

            GlassCard(
              modifier = Modifier.fillMaxWidth(),
              backgroundAlpha = if (isUser) 0.14f else 0.05f,
              borderAlpha = if (isUser) 0.35f else 0.12f
            ) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  // Rank Pill
                  Box(
                    modifier = Modifier
                      .clip(RoundedCornerShape(6.dp))
                      .background(
                        when (entry.rank) {
                          1 -> PlatinumWhite
                          2 -> Color.White.copy(alpha = 0.2f)
                          3 -> Color.White.copy(alpha = 0.12f)
                          else -> Color.Transparent
                        }
                      )
                      .border(
                        0.5.dp,
                        Color.White.copy(alpha = 0.2f),
                        RoundedCornerShape(6.dp)
                      )
                      .padding(horizontal = 6.dp, vertical = 3.dp)
                  ) {
                    Text(
                      text = rankString,
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Black,
                      color = if (entry.rank == 1) ObsidianPure else PlatinumWhite
                    )
                  }

                  Spacer(modifier = Modifier.width(10.dp))

                  // Initials Avatar
                  Box(
                    modifier = Modifier
                      .size(36.dp)
                      .clip(CircleShape)
                      .background(
                        if (isUser) Brush.linearGradient(listOf(Color.White.copy(alpha = 0.25f), Color.White.copy(alpha = 0.08f)))
                        else Brush.linearGradient(listOf(Color.White.copy(alpha = 0.12f), Color.White.copy(alpha = 0.04f)))
                      )
                      .border(1.dp, Color.White.copy(alpha = 0.25f), CircleShape),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = initials,
                      color = PlatinumWhite,
                      fontSize = 12.sp,
                      fontWeight = FontWeight.Bold
                    )
                  }

                  Spacer(modifier = Modifier.width(10.dp))

                  Column {
                    Text(
                      text = entry.name + if (isUser) " (You)" else "",
                      color = PlatinumWhite,
                      fontSize = 14.sp,
                      fontWeight = if (isUser) FontWeight.Bold else FontWeight.Medium
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                      Box(modifier = Modifier.size(4.dp).clip(CircleShape).background(GravityAmber))
                      Spacer(modifier = Modifier.width(4.dp))
                      Text(
                        text = "${entry.streak} Day Active Streak",
                        color = SilverMedium,
                        fontSize = 11.sp
                      )
                    }
                  }
                }

                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(GravityAmber.copy(alpha = 0.12f))
                    .border(0.5.dp, GravityAmber.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 9.dp, vertical = 4.dp)
                ) {
                  Text(
                    text = "${entry.gravityPoints} GP",
                    color = GravityAmber,
                    fontSize = 11.sp,
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
            text = "CLASSROOM PEERS & STUDY CIRCLE",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(8.dp))

          LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(friends) { friend ->
              val initials = friend.name.split(" ").mapNotNull { it.firstOrNull()?.toString() }.take(2).joinToString("")
              Column(
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .background(Color.White.copy(alpha = 0.05f))
                  .border(0.5.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(12.dp))
                  .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
              ) {
                Box(
                  modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.10f))
                    .border(1.dp, Color.White.copy(alpha = 0.20f), CircleShape),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = initials,
                    color = PlatinumWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                  )
                  if (friend.isOnline) {
                    Box(
                      modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(MasteryEmerald)
                        .border(1.dp, ObsidianPure, CircleShape)
                        .align(Alignment.BottomEnd)
                    )
                  }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = friend.name.split(" ").first(), color = PlatinumWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                Text(text = "${friend.gravityPoints} GP", color = GravityAmber, fontSize = 9.sp)
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Study Room Chat
          Text(
            text = "STUDY ROOM DIRECT FORUM",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )

          Spacer(modifier = Modifier.height(8.dp))

          LazyColumn(
            modifier = Modifier
              .weight(1f)
              .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            items(chatMessages) { msg ->
              val isUser = msg.senderName == userProfile.fullName
              Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = if (isUser) Alignment.End else Alignment.Start
              ) {
                Text(
                  text = msg.senderName,
                  color = SilverMedium,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(2.dp))
                Box(
                  modifier = Modifier
                    .widthIn(max = 280.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isUser) Color.White.copy(alpha = 0.15f) else Color.White.copy(alpha = 0.05f))
                    .border(
                      0.5.dp,
                      if (isUser) PlatinumWhite.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.1f),
                      RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                  Text(
                    text = msg.messageText,
                    color = PlatinumWhite,
                    fontSize = 13.sp,
                    lineHeight = 17.sp
                  )
                  if (!msg.formulaSnippet.isNullOrBlank()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                      text = "Formula: ${msg.formulaSnippet}",
                      color = GravityAmber,
                      fontSize = 11.sp,
                      fontWeight = FontWeight.SemiBold
                    )
                  }
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Chat Input Row
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(24.dp))
              .background(Color.White.copy(alpha = 0.06f))
              .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(24.dp))
              .padding(horizontal = 14.dp, vertical = 2.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            TextField(
              value = chatInput,
              onValueChange = { chatInput = it },
              placeholder = { Text("Share doubt, formula, or proof...", color = SilverMuted, fontSize = 12.sp) },
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
              Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = PlatinumWhite, modifier = Modifier.size(18.dp))
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
                Icon(Icons.Default.School, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(20.dp))
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
            Text("CRITICAL WEAK TOPIC ALERTS (NEEDS ATTENTION)", color = UrgentRose, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
          }

          item {
            GlassCard(modifier = Modifier.fillMaxWidth()) {
              Row(verticalAlignment = Alignment.Top) {
                Icon(Icons.Default.PriorityHigh, contentDescription = null, tint = UrgentRose, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                  Text("14 Students Struggling with Ray Diagram Conventions in Chapter 10", color = PlatinumWhite, fontSize = 13.sp, fontWeight = FontWeight.Bold)
                  Spacer(modifier = Modifier.height(3.dp))
                  Text("Recommended Teacher Action: Broadcast an exemplar walkthrough or assign a 5-question Socratic drill.", color = SilverMedium, fontSize = 11.sp)
                }
              }
            }
          }

          item {
            Text("STUDENT ROSTER & SUBMISSION STATUS", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
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
