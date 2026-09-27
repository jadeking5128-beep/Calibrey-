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
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun ExamWarRoomScreen(
  ncertClass: NcertClass,
  onBackClick: () -> Unit,
  onStartExamSprint: () -> Unit,
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
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PlatinumWhite)
      }
      Spacer(modifier = Modifier.width(12.dp))
      Column {
        Text(
          text = "HIGH-STAKES COMMAND CENTER",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "Exam War Room",
          color = PlatinumWhite,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 1. Board Exam Countdown Timer
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "CBSE BOARD EXAM COUNTDOWN",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(10.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        listOf(
          "42" to "DAYS",
          "14" to "HOURS",
          "38" to "MINS",
          "19" to "SECS"
        ).forEach { (value, label) ->
          Column(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(10.dp))
              .background(Color.White.copy(alpha = 0.05f))
              .border(0.5.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(10.dp))
              .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Text(text = value, color = PlatinumWhite, fontSize = 22.sp, fontWeight = FontWeight.Black)
            Text(text = label, color = SilverMedium, fontSize = 9.sp, fontWeight = FontWeight.Bold)
          }
          if (label != "SECS") Spacer(modifier = Modifier.width(6.dp))
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // 2. Syllabus & Readiness Gauges
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      GlassCard(modifier = Modifier.weight(1f)) {
        Text("SYLLABUS COVERAGE", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Text("74%", color = PlatinumWhite, fontSize = 22.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(6.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.White.copy(alpha = 0.1f))
        ) {
          Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.74f).background(PlatinumWhite))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("10 of 14 Chapters done", color = SilverMuted, fontSize = 10.sp)
      }

      GlassCard(modifier = Modifier.weight(1f)) {
        Text("REVISION READINESS", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(6.dp))
        Text("82%", color = MasteryEmerald, fontSize = 22.sp, fontWeight = FontWeight.Black)
        Spacer(modifier = Modifier.height(6.dp))
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.White.copy(alpha = 0.1f))
        ) {
          Box(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.82f).background(MasteryEmerald))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text("High formula retention", color = SilverMuted, fontSize = 10.sp)
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 3. High-Yield Triage List
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "HIGH-YIELD BOARD EXAM TRIAGE",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(8.dp))

      listOf(
        "Light: Reflection & Refraction (Ray Diagrams & Power)" to ("12 Marks" to MasteryEmerald),
        "Chemical Reactions & Equations (Balancing & Types)" to ("9 Marks" to PlatinumWhite),
        "Electricity (Joule Heating & Resistance Circuits)" to ("10 Marks" to GravityAmber),
        "Quadratic Equations (Discriminant Word Problems)" to ("8 Marks" to PlatinumWhite)
      ).forEach { (chapter, info) ->
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White.copy(alpha = 0.04f))
            .padding(horizontal = 10.dp, vertical = 8.dp),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(text = chapter, color = SilverBright, fontSize = 12.sp, modifier = Modifier.weight(1f))
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(info.second.copy(alpha = 0.15f))
              .padding(horizontal = 6.dp, vertical = 2.dp)
          ) {
            Text(text = info.first, color = info.second, fontSize = 10.sp, fontWeight = FontWeight.Bold)
          }
        }
        Spacer(modifier = Modifier.height(6.dp))
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // 4. Start Exam Sprint
    GlassButton(
      text = "START 20-MIN EXAM SPRINT (+200 GP)",
      onClick = onStartExamSprint,
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(80.dp))
  }
}
