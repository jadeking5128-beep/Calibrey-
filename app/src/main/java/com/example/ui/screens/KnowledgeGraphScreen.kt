package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.curriculum.NcertCurriculumData
import com.example.model.KnowledgeNode
import com.example.model.NcertClass
import com.example.model.Subject
import com.example.ui.components.KnowledgeGraphView
import com.example.ui.theme.*

@Composable
fun KnowledgeGraphScreen(
  ncertClass: NcertClass,
  onBackClick: () -> Unit,
  onNodeSelected: (KnowledgeNode) -> Unit,
  modifier: Modifier = Modifier
) {
  val subjects = remember(ncertClass) {
    NcertCurriculumData.getSubjectsForClass(ncertClass)
  }
  var selectedSubject by remember(ncertClass) {
    mutableStateOf(subjects.firstOrNull() ?: Subject("c10_sci", "Science", "SCI", "science", ncertClass, 13))
  }

  val nodes = remember(selectedSubject.id) {
    NcertCurriculumData.getKnowledgeNodesForSubject(selectedSubject.id)
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
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
          text = "INTERACTIVE CURRICULUM",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "NCERT Knowledge Graph",
          color = PlatinumWhite,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Subject Pills
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(subjects) { subject ->
        val isSelected = selectedSubject.id == subject.id
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.08f))
            .border(1.dp, if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
            .clickable { selectedSubject = subject }
            .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
          Text(
            text = subject.name,
            color = if (isSelected) ObsidianVoid else PlatinumWhite,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Interactive Graph Canvas
    KnowledgeGraphView(
      nodes = nodes,
      onNodeClick = onNodeSelected
    )
  }
}
