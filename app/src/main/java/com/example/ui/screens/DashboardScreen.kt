package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.NcertCurriculumData
import com.example.model.*
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.components.StudyHeatmapView
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
  userProfile: UserProfile,
  activityLogs: List<HeatmapDay>,
  dailyMissions: List<DailyMission>,
  onNavigateToCurriculum: () -> Unit,
  onNavigateToChapter: (Chapter) -> Unit,
  onNavigateToKnowledgeGraph: () -> Unit,
  onNavigateToExamWarRoom: () -> Unit,
  onNavigateToStudyDna: () -> Unit,
  onNavigateToAiTutor: () -> Unit,
  onNavigateToRevision: () -> Unit,
  onClaimMission: (DailyMission) -> Unit,
  modifier: Modifier = Modifier
) {
  val highYieldChapters = NcertCurriculumData.getChaptersForSubject(
    if (userProfile.ncertClass == NcertClass.CLASS_10) "c10_sci" else "c9_sci"
  ).take(3)

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // 1. Hero Student Rank & Level Card
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Text(
            text = userProfile.ncertClass.displayName.uppercase(),
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = userProfile.fullName,
            color = PlatinumWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = userProfile.rankTitle,
            color = GravityAmber,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold
          )
        }

        // Level Circular Badge
        Box(
          modifier = Modifier
            .size(54.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.12f))
            .border(1.5.dp, Color.White.copy(alpha = 0.35f), CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("LVL", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold)
            Text("${userProfile.level}", color = PlatinumWhite, fontSize = 18.sp, fontWeight = FontWeight.Black)
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Level Progress Bar
      Column {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Text(
            text = "${userProfile.gravityPoints} Gravity Points",
            color = SilverBright,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
          )
          Text(
            text = "Next: Level ${userProfile.level + 1}",
            color = SilverMedium,
            fontSize = 11.sp
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(6.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(Color.White.copy(alpha = 0.1f))
        ) {
          Box(
            modifier = Modifier
              .fillMaxHeight()
              .fillMaxWidth(userProfile.levelProgress)
              .clip(RoundedCornerShape(3.dp))
              .background(PlatinumWhite)
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 2. Exam War Room & Quick Access Grid
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      // Exam War Room Shortcut
      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToExamWarRoom
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("⚔️", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text("Exam War Room", color = PlatinumWhite, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("Countdown: 42 Days\nReadiness: 78%", color = SilverMedium, fontSize = 11.sp, lineHeight = 15.sp)
      }

      // AI Study DNA Shortcut
      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToStudyDna
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("🧬", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text("Study DNA", color = PlatinumWhite, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("Velocity: 8.4/10\nRetention: 88%", color = SilverMedium, fontSize = 11.sp, lineHeight = 15.sp)
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 3. AI Socratic Recommendation Card
    GlassCard(
      modifier = Modifier.fillMaxWidth(),
      onClick = onNavigateToAiTutor
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(8.dp))
          Text("CALIBREY AI INSIGHT", color = PlatinumWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
        }
        Text("Ask AI ->", color = SilverMedium, fontSize = 11.sp)
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Your memory decay curve in 'Acids, Bases & Salts' indicates pH calculations need reinforcement. Suggested: Solve 3 NCERT exemplar questions.",
        color = SilverBright,
        fontSize = 13.sp,
        lineHeight = 18.sp
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 4. Daily Missions & Challenges
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "DAILY MISSIONS & CHALLENGES",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "+360 GP Possible",
          color = GravityAmber,
          fontSize = 11.sp,
          fontWeight = FontWeight.SemiBold
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      dailyMissions.forEach { mission ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (mission.isCompleted) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.03f))
            .border(0.5.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .padding(horizontal = 12.dp, vertical = 10.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = mission.title,
              color = if (mission.isCompleted) SilverMuted else PlatinumWhite,
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold
            )
            Text(
              text = mission.description,
              color = SilverMuted,
              fontSize = 11.sp
            )
          }

          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(GravityAmber.copy(alpha = 0.15f))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(
                text = "+${mission.rewardGp} GP",
                color = GravityAmber,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }

            Spacer(modifier = Modifier.width(8.dp))

            if (mission.isCompleted) {
              Icon(Icons.Default.CheckCircle, contentDescription = "Completed", tint = MasteryEmerald, modifier = Modifier.size(20.dp))
            } else {
              Box(
                modifier = Modifier
                  .size(20.dp)
                  .clip(CircleShape)
                  .border(1.5.dp, SilverMuted, CircleShape)
                  .clickable { onClaimMission(mission) }
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 5. 8-Week Activity Heatmap
    StudyHeatmapView(activityLogs = activityLogs)

    Spacer(modifier = Modifier.height(14.dp))

    // 6. High-Yield Chapters
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "HIGH-YIELD CHAPTERS",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Text(
        text = "View All ->",
        color = PlatinumWhite,
        fontSize = 12.sp,
        modifier = Modifier.clickable { onNavigateToCurriculum() }
      )
    }

    Spacer(modifier = Modifier.height(8.dp))

    highYieldChapters.forEach { chapter ->
      GlassCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onNavigateToChapter(chapter) }
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "Chapter ${chapter.chapterNumber}",
              color = SilverMedium,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = chapter.title,
              color = PlatinumWhite,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "${chapter.estimatedMinutes} mins • ${chapter.subtopics.size} subtopics • ${chapter.difficulty.name}",
              color = SilverMuted,
              fontSize = 11.sp
            )
          }

          Icon(
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = null,
            tint = SilverMedium,
            modifier = Modifier.size(14.dp)
          )
        }
      }
      Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(72.dp))
  }
}
