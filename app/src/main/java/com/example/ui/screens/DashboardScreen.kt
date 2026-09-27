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
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.NcertCurriculumData
import com.example.model.*
import com.example.ui.components.GlassCard
import com.example.ui.components.GlassStatusPill
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
      .background(ObsidianPure)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // 1. Primary Operating System Command Header
    GlassCard(
      modifier = Modifier.fillMaxWidth(),
      backgroundAlpha = 0.08f
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(6.dp)
                .clip(CircleShape)
                .background(MasteryEmerald)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = userProfile.ncertClass.displayName.uppercase(),
              color = SilverMedium,
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.5.sp
            )
          }

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = userProfile.fullName.ifBlank { "Student" },
            color = PlatinumWhite,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.5).sp
          )

          Spacer(modifier = Modifier.height(3.dp))

          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = userProfile.rankTitle.uppercase(),
              color = GravityAmber,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "•  ${userProfile.totalMinutesStudied}M LOGGED",
              color = SilverMuted,
              fontSize = 11.sp,
              fontWeight = FontWeight.Medium
            )
          }
        }

        // Concentric Level Orb
        Box(
          modifier = Modifier
            .size(56.dp)
            .clip(CircleShape)
            .background(
              Brush.radialGradient(
                listOf(Color.White.copy(alpha = 0.18f), Color.White.copy(alpha = 0.03f))
              )
            )
            .border(
              1.dp,
              Brush.verticalGradient(
                listOf(Color.White.copy(alpha = 0.40f), Color.White.copy(alpha = 0.10f))
              ),
              CircleShape
            ),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = "LEVEL",
              color = SilverMedium,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
            Text(
              text = "${userProfile.level}",
              color = PlatinumWhite,
              fontSize = 19.sp,
              fontWeight = FontWeight.Black
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Linear Progression Rail
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
            text = "${(userProfile.levelProgress * 100).toInt()}% TO LEVEL ${userProfile.level + 1}",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.5.sp
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(5.dp)
            .clip(RoundedCornerShape(3.dp))
            .background(Color.White.copy(alpha = 0.08f))
        ) {
          Box(
            modifier = Modifier
              .fillMaxHeight()
              .fillMaxWidth(userProfile.levelProgress)
              .clip(RoundedCornerShape(3.dp))
              .background(
                Brush.horizontalGradient(
                  listOf(Color.White.copy(alpha = 0.6f), PlatinumWhite)
                )
              )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 2. High-Precision Command Modules: War Room & Study DNA
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      // Exam War Room
      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToExamWarRoom
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "WAR ROOM",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(UrgentRose)
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "42 Days",
          color = PlatinumWhite,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "Syllabus 74% • Readiness 82%",
          color = SilverMedium,
          fontSize = 11.sp
        )
      }

      // Cognitive Study DNA
      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToStudyDna
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "STUDY DNA",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Box(
            modifier = Modifier
              .size(6.dp)
              .clip(CircleShape)
              .background(ElectricCyan)
          )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text(
          text = "8.4 Velocity",
          color = PlatinumWhite,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = "Retention 88% • Pred. 93%",
          color = SilverMedium,
          fontSize = 11.sp
        )
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 3. Calibrey AI Socratic Telemetry Feed
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
          Icon(
            imageVector = Icons.Default.AutoAwesome,
            contentDescription = null,
            tint = PlatinumWhite,
            modifier = Modifier.size(15.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "CALIBREY AI SOCRATIC DIRECTIVE",
            color = PlatinumWhite,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        }
        Text(
          text = "OPEN TUTOR →",
          color = SilverMedium,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      Text(
        text = "Memory decay analysis detects conceptual vulnerability in 'Acids, Bases & Salts' (pH calculations). Recommendation: Execute 1 targeted NCERT active recall drill.",
        color = SilverBright,
        fontSize = 13.sp,
        lineHeight = 18.sp
      )
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 4. Daily Missions & Precision Challenges
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "DAILY MISSIONS & OBJECTIVES",
          color = SilverMedium,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "+360 GP POOL",
          color = GravityAmber,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 0.5.sp
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      dailyMissions.forEach { mission ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(if (mission.isCompleted) Color.White.copy(alpha = 0.05f) else Color.White.copy(alpha = 0.02f))
            .border(
              1.dp,
              if (mission.isCompleted) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.06f),
              RoundedCornerShape(10.dp)
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
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
                .background(GravityAmber.copy(alpha = 0.12f))
                .border(0.5.dp, GravityAmber.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
              Text(
                text = "+${mission.rewardGp} GP",
                color = GravityAmber,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }

            Spacer(modifier = Modifier.width(10.dp))

            if (mission.isCompleted) {
              Icon(
                Icons.Default.CheckCircle,
                contentDescription = "Completed",
                tint = MasteryEmerald,
                modifier = Modifier.size(18.dp)
              )
            } else {
              Box(
                modifier = Modifier
                  .size(18.dp)
                  .clip(CircleShape)
                  .border(1.5.dp, SilverMedium, CircleShape)
                  .clickable { onClaimMission(mission) }
              )
            }
          }
        }
        Spacer(modifier = Modifier.height(6.dp))
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 5. 8-Week Consistency Heatmap
    StudyHeatmapView(activityLogs = activityLogs)

    Spacer(modifier = Modifier.height(14.dp))

    // 6. High-Yield Curriculum Modules
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = "HIGH-PRIORITY NCERT CHAPTERS",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Text(
        text = "ALL CHAPTERS →",
        color = PlatinumWhite,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "CHAPTER ${chapter.chapterNumber}",
                color = SilverMedium,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
              )
              Spacer(modifier = Modifier.width(8.dp))
              GlassStatusPill(
                label = chapter.difficulty.name,
                accentColor = when (chapter.difficulty) {
                  ChapterDifficulty.FOUNDATIONAL -> MasteryEmerald
                  ChapterDifficulty.CORE -> PlatinumWhite
                  ChapterDifficulty.ADVANCED -> GravityAmber
                }
              )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
              text = chapter.title,
              color = PlatinumWhite,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "${chapter.estimatedMinutes} mins • ${chapter.subtopics.size} subtopics",
              color = SilverMuted,
              fontSize = 11.sp
            )
          }

          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = null,
            tint = SilverMedium,
            modifier = Modifier.size(13.dp)
          )
        }
      }
      Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(72.dp))
  }
}
