package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NcertClass
import com.example.model.UserProfile
import com.example.ui.components.GlassCard
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
      .background(ObsidianVoid)
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
      ) {
        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PlatinumWhite)
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "COGNITIVE ANALYTICS",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "AI Study DNA",
          color = PlatinumWhite,
          fontSize = 18.sp,
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
        Text("⚡ VELOCITY", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("8.4 / 10", color = PlatinumWhite, fontSize = 16.sp, fontWeight = FontWeight.Black)
        Text("Fast concept intake", color = SilverMuted, fontSize = 10.sp)
      }
      // Retention
      GlassCard(modifier = Modifier.weight(1f)) {
        Text("🧠 RETENTION", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("88%", color = MasteryEmerald, fontSize = 16.sp, fontWeight = FontWeight.Black)
        Text("High formula recall", color = SilverMuted, fontSize = 10.sp)
      }
      // Consistency
      GlassCard(modifier = Modifier.weight(1f)) {
        Text("🔥 STREAK", color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("94%", color = GravityAmber, fontSize = 16.sp, fontWeight = FontWeight.Black)
        Text("Top 5% peer habit", color = SilverMuted, fontSize = 10.sp)
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
        Column {
          Text("PREDICTED BOARD EXAM PERFORMANCE", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
          Spacer(modifier = Modifier.height(4.dp))
          Text("92% - 95% (Distinction Bracket)", color = PlatinumWhite, fontSize = 16.sp, fontWeight = FontWeight.Bold)
          Text("Based on 18 quizzes and NCERT exemplar mastery", color = SilverMuted, fontSize = 11.sp)
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

    // 3. Cognitive Strengths & Weaknesses
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("✅", fontSize = 14.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text("STRENGTHS", color = MasteryEmerald, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(6.dp))
        listOf(
          "Ray Diagrams & Optics",
          "Quadratic Middle Term Splitting",
          "Redox Reaction Balancing",
          "Ohm's Law Circuit Proofs"
        ).forEach {
          Text("• $it", color = SilverBright, fontSize = 11.sp, modifier = Modifier.padding(vertical = 2.dp))
        }
      }

      GlassCard(modifier = Modifier.weight(1f)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("⚠️", fontSize = 14.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text("ATTENTION NEEDED", color = UrgentRose, fontSize = 11.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(6.dp))
        listOf(
          "Cartesian Sign in Lenses",
          "Chlor-Alkali Electrolysis",
          "Trigonometric Proof Identities",
          "Resistance in Parallel Units"
        ).forEach {
          Text("• $it", color = SilverBright, fontSize = 11.sp, modifier = Modifier.padding(vertical = 2.dp))
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 4. AI Strategic Recommendations
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(16.dp))
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "CALIBREY AI ACTION PLAN",
          color = PlatinumWhite,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
      }
      Spacer(modifier = Modifier.height(8.dp))
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
          modifier = Modifier.padding(vertical = 4.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(80.dp))
  }
}
