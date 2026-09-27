package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.data.curriculum.NcertCurriculumData
import com.example.data.local.ChapterProgressEntity
import com.example.model.Chapter
import com.example.model.ChapterDifficulty
import com.example.model.NcertClass
import com.example.model.Subject
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun CurriculumScreen(
  ncertClass: NcertClass,
  chapterProgressMap: Map<String, ChapterProgressEntity>,
  onChapterSelected: (Chapter) -> Unit,
  onToggleBookmark: (String, Boolean) -> Unit,
  modifier: Modifier = Modifier
) {
  val subjects = remember(ncertClass) {
    NcertCurriculumData.getSubjectsForClass(ncertClass)
  }
  var selectedSubject by remember(ncertClass) {
    mutableStateOf(subjects.firstOrNull() ?: Subject("c10_sci", "Science", "SCI", "science", ncertClass, 13))
  }
  var searchQuery by remember { mutableStateOf("") }

  val chapters = remember(selectedSubject.id) {
    NcertCurriculumData.getChaptersForSubject(selectedSubject.id)
  }

  val filteredChapters = chapters.filter {
    searchQuery.isBlank() ||
      it.title.contains(searchQuery, ignoreCase = true) ||
      it.subtitle.contains(searchQuery, ignoreCase = true) ||
      it.summary.contains(searchQuery, ignoreCase = true)
  }

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Subject Pills Row
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
            .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
          Text(
            text = subject.name,
            color = if (isSelected) ObsidianVoid else PlatinumWhite,
            fontSize = 13.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Search Box
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = { Text("Search NCERT chapters, topics, formulas...") },
      leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = SilverMedium) },
      trailingIcon = {
        if (searchQuery.isNotEmpty()) {
          IconButton(onClick = { searchQuery = "" }) {
            Icon(Icons.Default.Close, contentDescription = "Clear", tint = SilverMedium)
          }
        }
      },
      colors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = PlatinumWhite,
        unfocusedTextColor = PlatinumWhite,
        focusedBorderColor = PlatinumWhite,
        unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
        focusedPlaceholderColor = SilverMuted,
        unfocusedPlaceholderColor = SilverMuted,
        cursorColor = PlatinumWhite
      ),
      modifier = Modifier.fillMaxWidth(),
      singleLine = true
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Chapters List
    LazyColumn(
      verticalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(bottom = 80.dp)
    ) {
      items(filteredChapters) { chapter ->
        val progress = chapterProgressMap[chapter.id]
        val isBookmarked = progress?.isBookmarked ?: chapter.isBookmarked
        val isCompleted = progress?.isCompleted ?: chapter.isCompleted
        val percent = progress?.progressPercent ?: 0f

        GlassCard(
          modifier = Modifier.fillMaxWidth(),
          onClick = { onChapterSelected(chapter) }
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                  text = "CHAPTER ${chapter.chapterNumber}",
                  color = SilverMedium,
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
                Spacer(modifier = Modifier.width(8.dp))

                val diffColor = when (chapter.difficulty) {
                  ChapterDifficulty.FOUNDATIONAL -> MasteryEmerald
                  ChapterDifficulty.CORE -> PlatinumWhite
                  ChapterDifficulty.ADVANCED -> GravityAmber
                }
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(diffColor.copy(alpha = 0.15f))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = chapter.difficulty.name,
                    color = diffColor,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Bold
                  )
                }
              }

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = chapter.title,
                color = PlatinumWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )

              Spacer(modifier = Modifier.height(2.dp))

              Text(
                text = chapter.subtitle,
                color = SilverMedium,
                fontSize = 12.sp,
                maxLines = 2
              )
            }

            // Bookmark Button
            IconButton(
              onClick = { onToggleBookmark(chapter.id, isBookmarked) },
              modifier = Modifier.size(36.dp)
            ) {
              Icon(
                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = if (isBookmarked) GravityAmber else SilverMedium
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          // Footer Info & Progress
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "⏱️ ${chapter.estimatedMinutes} mins • 📑 ${chapter.subtopics.size} subtopics",
              color = SilverMuted,
              fontSize = 11.sp
            )

            if (isCompleted) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.CheckCircle, contentDescription = null, tint = MasteryEmerald, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Completed", color = MasteryEmerald, fontSize = 11.sp, fontWeight = FontWeight.Bold)
              }
            } else if (percent > 0f) {
              Text(
                text = "${(percent * 100).toInt()}% Done",
                color = PlatinumWhite,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold
              )
            }
          }
        }
      }
    }
  }
}
