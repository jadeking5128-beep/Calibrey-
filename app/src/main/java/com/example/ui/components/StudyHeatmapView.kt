package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.HeatmapDay
import com.example.ui.theme.*

@Composable
fun StudyHeatmapView(
  activityLogs: List<HeatmapDay>,
  modifier: Modifier = Modifier
) {
  var selectedDay by remember { mutableStateOf<HeatmapDay?>(null) }

  GlassCard(modifier = modifier.fillMaxWidth()) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "STUDY CONSISTENCY MATRIX",
          color = SilverMedium,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
          text = "8-Week Active Learning Rhythm",
          color = PlatinumWhite,
          fontSize = 14.sp,
          fontWeight = FontWeight.Bold
        )
      }

      // Minimalist Monochrome Intensity Legend
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text("LESS", color = SilverMuted, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(5.dp))
        for (i in 0..4) {
          Box(
            modifier = Modifier
              .size(9.dp)
              .clip(RoundedCornerShape(2.dp))
              .background(getHeatmapColor(i))
              .border(0.5.dp, Color.White.copy(alpha = 0.10f), RoundedCornerShape(2.dp))
          )
          Spacer(modifier = Modifier.width(2.5.dp))
        }
        Spacer(modifier = Modifier.width(2.5.dp))
        Text("MORE", color = SilverMuted, fontSize = 9.sp, fontWeight = FontWeight.Bold)
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Matrix Grid (7 rows for days of week, columns for weeks)
    val weeks = activityLogs.chunked(7)
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      weeks.forEach { weekDays ->
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          weekDays.forEach { day ->
            val isSelected = selectedDay?.date == day.date
            Box(
              modifier = Modifier
                .size(13.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(getHeatmapColor(day.intensity))
                .border(
                  width = if (isSelected) 1.5.dp else 0.5.dp,
                  color = if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.12f),
                  shape = RoundedCornerShape(3.dp)
                )
                .clickable { selectedDay = day }
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Selected day tooltip or summary
    val dayToShow = selectedDay ?: activityLogs.lastOrNull()
    if (dayToShow != null) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(10.dp))
          .background(Color.White.copy(alpha = 0.04f))
          .border(0.5.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(10.dp))
          .padding(horizontal = 12.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = dayToShow.date,
          color = SilverBright,
          fontSize = 11.sp,
          fontWeight = FontWeight.Medium
        )
        Text(
          text = "${dayToShow.minutesStudied}m logged • ${dayToShow.quizzesCompleted} assessment(s)",
          color = SilverMedium,
          fontSize = 11.sp
        )
      }
    }
  }
}

private fun getHeatmapColor(intensity: Int): Color {
  return when (intensity) {
    0 -> Color(0xFF111116)
    1 -> Color(0xFF22222D)
    2 -> Color(0xFF444458)
    3 -> Color(0xFF7A7A94)
    4 -> PlatinumWhite
    else -> Color(0xFF111116)
  }
}
