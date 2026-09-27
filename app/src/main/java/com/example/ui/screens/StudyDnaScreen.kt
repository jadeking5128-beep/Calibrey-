package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PriorityHigh
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
import com.example.model.NcertClass
import com.example.model.UserProfile
import com.example.ui.components.GlassCard
import com.example.ui.components.GlassStatusPill
import com.example.ui.theme.*

@Composable
fun StudyDnaScreen(
  userProfile: UserProfile,
  onBackClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianPure)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Header
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
          .border(1.dp, Color.White.copy(alpha = 0.15f), CircleShape)
      ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PlatinumWhite)
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "COGNITIVE ANALYTICS",
          color = SilverMedium,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.2.sp
        )
        Text(
          text = "AI Study DNA",
          color = PlatinumWhite,
          fontSize = 20.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Metric Overview Cards
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      // Velocity
      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(PlatinumWhite))
          Spacer(modifier = Modifier.width(5.dp))
          Text("VELOCITY", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text("8.4 / 10", color = PlatinumWhite, fontSize = 16.sp, fontWeight = FontWeight.Black)
        Text("Concept intake rate", color = SilverMuted, fontSize = 10.sp)
      }

      // Retention
      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(MasteryEmerald))
          Spacer(modifier = Modifier.width(5.dp))
          Text("RETENTION", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text("88%", color = MasteryEmerald, fontSize = 16.sp, fontWeight = FontWeight.Black)
        Text("Active recall index", color = SilverMuted, fontSize = 10.sp)
      }

      // Consistency
      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(GravityAmber))
          Spacer(modifier = Modifier.width(5.dp))
          Text("RHYTHM", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.8.sp)
        }
        Spacer(modifier = Modifier.height(6.dp))
        Text("94%", color = GravityAmber, fontSize = 16.sp, fontWeight = FontWeight.Black)
        Text("Habit stability", color = SilverMuted, fontSize = 10.sp)
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 2. Predicted Exam Readiness
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            "PREDICTED BOARD EXAM PERFORMANCE",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text("92% - 95% (Distinction Bracket)", color = PlatinumWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
          Spacer(modifier = Modifier.height(2.dp))
          Text("Calculated from 18 chapter quizzes, spaced recall, and NCERT exemplars", color = SilverMuted, fontSize = 11.sp)
        }

        Box(
          modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(MasteryEmerald.copy(alpha = 0.15f))
            .border(1.5.dp, MasteryEmerald, CircleShape),
          contentAlignment = Alignment.Center
        ) {
          Text("A+", color = MasteryEmerald, fontSize = 18.sp, fontWeight = FontWeight.Black)
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 3. Subject Mastery Breakdown
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "CURRICULAR MASTERY SPECTRUM",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(10.dp))

      val subjectMasteries = listOf(
        "Science / Chemistry" to 0.91f,
        "Science / Physics" to 0.78f,
        "Science / Biology" to 0.86f,
        "Mathematics / Algebra" to 0.94f,
        "Mathematics / Geometry" to 0.72f
      )

      subjectMasteries.forEach { (subject, mastery) ->
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text(subject, color = SilverBright, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            Text("${(mastery * 100).toInt()}%", color = PlatinumWhite, fontSize = 11.sp, fontWeight = FontWeight.Bold)
          }
          Spacer(modifier = Modifier.height(4.dp))
          Box(
            modifier = Modifier
              .fillMaxWidth()
              .height(4.dp)
              .clip(RoundedCornerShape(2.dp))
              .background(Color.White.copy(alpha = 0.08f))
          ) {
            Box(
              modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(mastery)
                .clip(RoundedCornerShape(2.dp))
                .background(
                  Brush.horizontalGradient(
                    listOf(Color.White.copy(alpha = 0.5f), PlatinumWhite)
                  )
                )
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 4. Cognitive Strengths & Weaknesses
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MasteryEmerald, modifier = Modifier.size(14.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("MASTERY STRONGHOLDS", color = MasteryEmerald, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        listOf(
          "Ray Diagrams & Optics",
          "Quadratic Middle Term",
          "Redox Reaction Balancing",
          "Ohm's Law Circuit Proofs"
        ).forEach {
          Text("• $it", color = SilverBright, fontSize = 11.sp, modifier = Modifier.padding(vertical = 2.dp))
        }
      }

      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(Icons.Default.PriorityHigh, contentDescription = null, tint = UrgentRose, modifier = Modifier.size(14.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("VULNERABLE TOPICS", color = UrgentRose, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
        }
        Spacer(modifier = Modifier.height(8.dp))
        listOf(
          "Cartesian Sign in Lenses",
          "Chlor-Alkali Electrolysis",
          "Trigonometric Proof Identities",
          "Parallel Resistance Units"
        ).forEach {
          Text("• $it", color = SilverBright, fontSize = 11.sp, modifier = Modifier.padding(vertical = 2.dp))
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 5. AI Strategic Recommendations
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(15.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
          text = "CALIBREY AI SOCRATIC ACTION PLAN",
          color = PlatinumWhite,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
      }
      Spacer(modifier = Modifier.height(10.dp))
      listOf(
        "1. Active Recall Spacing: Spend 10 minutes every alternate day reviewing 'Acids, Bases & Salts' formulas before sleep.",
        "2. Interleaved Practice: Alternate between 1 numerical physics problem and 1 algebraic trigonometry identity to build cognitive agility.",
        "3. Error Log Review: Revisit the 4 incorrect questions in your last 'Electricity' quiz using the Calibrey Doubt Solver."
      ).forEach { plan ->
        Text(
          text = plan,
          color = SilverBright,
          fontSize = 12.sp,
          lineHeight = 17.sp,
          modifier = Modifier.padding(vertical = 3.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(80.dp))
  }
}
