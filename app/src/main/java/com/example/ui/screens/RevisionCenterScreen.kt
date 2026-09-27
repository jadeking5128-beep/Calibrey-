package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NcertClass
import com.example.model.RevisionItem
import com.example.model.RevisionUrgency
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun RevisionCenterScreen(
  ncertClass: NcertClass,
  onNavigateToKnowledgeGraph: () -> Unit,
  onNavigateToWarRoom: () -> Unit,
  onNavigateToStudyDna: () -> Unit,
  onNavigateToSavedNotes: () -> Unit,
  onStartRevisionDrill: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  // Spaced repetition items
  val revisionItems = remember {
    listOf(
      RevisionItem("r1", "c10_sci_ch2", "Acids, Bases & Salts", "Science", 60, System.currentTimeMillis() - 86400000L * 4, RevisionUrgency.URGENT, 0.75f, listOf("pH Calculations", "Chlor-Alkali Process")),
      RevisionItem("r2", "c10_math_ch4", "Quadratic Equations", "Mathematics", 68, System.currentTimeMillis() - 86400000L * 3, RevisionUrgency.URGENT, 0.65f, listOf("Discriminant Nature of Roots")),
      RevisionItem("r3", "c10_sci_ch1", "Chemical Reactions & Equations", "Science", 82, System.currentTimeMillis() - 86400000L * 2, RevisionUrgency.RECOMMENDED, 0.35f, listOf("Balancing Complex Equations")),
      RevisionItem("r4", "c10_sci_ch10", "Light: Reflection & Refraction", "Science", 94, System.currentTimeMillis() - 86400000L * 1, RevisionUrgency.MASTERED, 0.10f, listOf("Lens Sign Convention"))
    )
  }

  var isFlashcardExpanded by remember { mutableStateOf(false) }
  var isFlashcardFlipped by remember { mutableStateOf(false) }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Header & Quick Navigation Shortcuts
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToKnowledgeGraph
      ) {
        Text("🗺️", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Knowledge Graph", color = PlatinumWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("Visual curriculum map", color = SilverMuted, fontSize = 10.sp)
      }

      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToWarRoom
      ) {
        Text("⚔️", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Exam War Room", color = PlatinumWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("Countdown & syllabus", color = SilverMuted, fontSize = 10.sp)
      }

      GlassCard(
        modifier = Modifier.weight(1f),
        onClick = onNavigateToSavedNotes
      ) {
        Text("📑", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text("Saved Notes", color = PlatinumWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Text("AI summaries library", color = SilverMuted, fontSize = 10.sp)
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Active Recall Flashcard Drill Widget
    GlassCard(
      modifier = Modifier.fillMaxWidth(),
      onClick = {
        isFlashcardExpanded = !isFlashcardExpanded
        isFlashcardFlipped = false
      }
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("⚡", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "ACTIVE RECALL FLASHCARD OF THE DAY",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
        }
        Text(if (isFlashcardExpanded) "Collapse" else "Expand", color = PlatinumWhite, fontSize = 11.sp)
      }

      if (isFlashcardExpanded) {
        Spacer(modifier = Modifier.height(12.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.06f))
            .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
            .clickable { isFlashcardFlipped = !isFlashcardFlipped }
            .padding(16.dp),
          contentAlignment = Alignment.Center
        ) {
          Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
              text = if (!isFlashcardFlipped) "QUESTION (Tap to flip)" else "NCERT ANSWER (Tap to flip back)",
              color = SilverMedium,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
              text = if (!isFlashcardFlipped)
                "Why do ionic compounds have high melting and boiling points?"
              else
                "Because of strong electrostatic forces of attraction between oppositely charged ions, which requires a large amount of energy to break.",
              color = PlatinumWhite,
              fontSize = 14.sp,
              fontWeight = FontWeight.SemiBold,
              lineHeight = 20.sp
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Adaptive Spaced Repetition Priority List
    Text(
      text = "ADAPTIVE SPACED REPETITION QUEUE",
      color = SilverMedium,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )

    Spacer(modifier = Modifier.height(8.dp))

    revisionItems.forEach { item ->
      val (badgeText, badgeColor) = when (item.urgency) {
        RevisionUrgency.URGENT -> "OVERDUE (HIGH DECAY)" to UrgentRose
        RevisionUrgency.RECOMMENDED -> "RECOMMENDED" to GravityAmber
        RevisionUrgency.MASTERED -> "MASTERED" to MasteryEmerald
      }

      GlassCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onStartRevisionDrill(item.chapterId) }
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(4.dp))
                  .background(badgeColor.copy(alpha = 0.15f))
                  .padding(horizontal = 6.dp, vertical = 2.dp)
              ) {
                Text(
                  text = badgeText,
                  color = badgeColor,
                  fontSize = 9.sp,
                  fontWeight = FontWeight.Bold
                )
              }
              Spacer(modifier = Modifier.width(6.dp))
              Text(text = item.subjectName, color = SilverMedium, fontSize = 11.sp)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
              text = item.chapterTitle,
              color = PlatinumWhite,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
              text = "Last Quiz: ${item.lastQuizScore}% • Weak Topics: ${item.weakConcepts.joinToString(", ")}",
              color = SilverMuted,
              fontSize = 11.sp
            )
          }

          Spacer(modifier = Modifier.width(8.dp))

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(PlatinumWhite)
              .padding(horizontal = 12.dp, vertical = 6.dp)
          ) {
            Text(
              text = "Revise",
              color = ObsidianVoid,
              fontSize = 12.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
      Spacer(modifier = Modifier.height(8.dp))
    }

    Spacer(modifier = Modifier.height(80.dp))
  }
}
