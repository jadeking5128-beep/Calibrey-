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
          text = "STUDY CONSISTENCY HEATMAP",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "8-Week Active Learning Matrix",
          color = PlatinumWhite,
          fontSize = 14.sp,
          fontWeight = FontWeight.SemiBold
        )
      }

      // Legend
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Less", color = SilverMuted, fontSize = 10.sp)
        Spacer(modifier = Modifier.width(4.dp))
        for (i in 0..4) {
          Box(
            modifier = Modifier
              .size(10.dp)
              .clip(RoundedCornerShape(2.dp))
              .background(getHeatmapColor(i))
              .border(0.5.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(2.dp))
          )
          Spacer(modifier = Modifier.width(2.dp))
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text("More", color = SilverMuted, fontSize = 10.sp)
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Matrix Grid (7 rows for days of week, 8 columns for weeks)
    // Group into chunks of 7 days
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
                .size(14.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(getHeatmapColor(day.intensity))
                .border(
                  width = if (isSelected) 1.5.dp else 0.5.dp,
                  color = if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.2f),
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
          .clip(RoundedCornerShape(8.dp))
          .background(Color.White.copy(alpha = 0.05f))
          .border(0.5.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
          .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "${dayToShow.date} (Activity level ${dayToShow.intensity}/4)",
          color = SilverBright,
          fontSize = 12.sp
        )
        Text(
          text = "${dayToShow.minutesStudied}m studied • ${dayToShow.quizzesCompleted} quizzes",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Medium
        )
      }
    }
  }
}

private fun getHeatmapColor(intensity: Int): Color {
  return when (intensity) {
    0 -> Color(0xFF1E1E24)
    1 -> Color(0xFF3F3F46)
    2 -> Color(0xFF71717A)
    3 -> Color(0xFFA1A1AA)
    4 -> PlatinumWhite
    else -> Color(0xFF1E1E24)
  }
}
