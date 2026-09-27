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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.SubtopicProgressEntity
import com.example.model.Chapter
import com.example.ui.components.EmbeddedVideoCard
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun ChapterDetailScreen(
  chapter: Chapter,
  subtopicProgressMap: Map<String, SubtopicProgressEntity>,
  onBackClick: () -> Unit,
  onTakeQuiz: () -> Unit,
  onGenerateNotes: () -> Unit,
  onAskAi: () -> Unit,
  onToggleSubtopic: (String, Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Top Bar with Back Arrow
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(
        onClick = onBackClick,
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .background(Color.White.copy(alpha = 0.08f))
      ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PlatinumWhite)
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "CHAPTER ${chapter.chapterNumber}",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = chapter.title,
          color = PlatinumWhite,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 1. YouTube Educational Lecture
    EmbeddedVideoCard(
      videoId = chapter.youtubeVideoId,
      title = chapter.videoTitle,
      duration = chapter.videoDuration
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Action Row (Quiz, Notes, AI)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      GlassButton(
        text = "Practice Quiz",
        onClick = onTakeQuiz,
        modifier = Modifier.weight(1f),
        icon = Icons.Default.Quiz
      )
      GlassButton(
        text = "AI Notes",
        onClick = onGenerateNotes,
        modifier = Modifier.weight(1f),
        isPrimary = false,
        icon = Icons.Default.AutoAwesome
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 2. Formula Cheat Sheet Card
    if (chapter.formulas.isNotEmpty()) {
      GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("⚡", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "KEY FORMULAS & GOVERNING LAWS",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        }
        Spacer(modifier = Modifier.height(8.dp))
        chapter.formulas.forEach { formula ->
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(Color.White.copy(alpha = 0.04f))
              .border(0.5.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
              .padding(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Text(
              text = formula,
              color = PlatinumWhite,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium
            )
          }
          Spacer(modifier = Modifier.height(4.dp))
        }
      }
      Spacer(modifier = Modifier.height(12.dp))
    }

    // 3. Subtopics Hierarchy & Progress
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "NCERT SUBTOPICS HIERARCHY",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "+50 GP / subtopic",
          color = GravityAmber,
          fontSize = 11.sp,
          fontWeight = FontWeight.SemiBold
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      chapter.subtopics.forEach { subtopic ->
        val progress = subtopicProgressMap[subtopic.id]
        val isCompleted = progress?.isCompleted ?: subtopic.isCompleted

        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (isCompleted) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.03f))
            .border(0.5.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
            .clickable { onToggleSubtopic(subtopic.id, isCompleted) }
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = "${subtopic.subtopicNumber} ${subtopic.title}",
              color = if (isCompleted) PlatinumWhite else SilverBright,
              fontSize = 13.sp,
              fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = subtopic.summary,
              color = SilverMuted,
              fontSize = 11.sp,
              lineHeight = 15.sp
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Box(
            modifier = Modifier
              .size(24.dp)
              .clip(CircleShape)
              .background(if (isCompleted) PlatinumWhite else Color.Transparent)
              .border(1.5.dp, if (isCompleted) PlatinumWhite else SilverMedium, CircleShape),
            contentAlignment = Alignment.Center
          ) {
            if (isCompleted) {
              Icon(Icons.Default.Check, contentDescription = null, tint = ObsidianVoid, modifier = Modifier.size(16.dp))
            }
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 4. Ask Socratic Tutor About Chapter
    GlassCard(
      modifier = Modifier.fillMaxWidth(),
      onClick = onAskAi
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text("CALIBREY TUTOR READY", color = PlatinumWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
          }
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = "Ask questions, clarify doubts, or request custom NCERT derivations for '${chapter.title}'.",
            color = SilverMedium,
            fontSize = 11.sp
          )
        }
        Icon(Icons.Default.ArrowForwardIos, contentDescription = null, tint = SilverMedium, modifier = Modifier.size(14.dp))
      }
    }

    Spacer(modifier = Modifier.height(80.dp))
  }
}
