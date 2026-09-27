package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import com.example.ui.components.GlassStatusPill
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
      .background(ObsidianPure)
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
            .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.05f))
            .border(
              1.dp,
              if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.12f),
              RoundedCornerShape(20.dp)
            )
            .clickable { selectedSubject = subject }
            .padding(horizontal = 16.dp, vertical = 7.dp)
        ) {
          Text(
            text = subject.name.uppercase(),
            color = if (isSelected) ObsidianPure else SilverBright,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            letterSpacing = 0.8.sp
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Search Box
    OutlinedTextField(
      value = searchQuery,
      onValueChange = { searchQuery = it },
      placeholder = { Text("Search NCERT syllabus, derivations, formulas...", color = SilverMuted, fontSize = 13.sp) },
      leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = SilverMedium, modifier = Modifier.size(18.dp)) },
      trailingIcon = {
        if (searchQuery.isNotEmpty()) {
          IconButton(onClick = { searchQuery = "" }) {
            Icon(Icons.Default.Close, contentDescription = "Clear", tint = SilverMedium, modifier = Modifier.size(16.dp))
          }
        }
      },
      shape = RoundedCornerShape(14.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = ObsidianElevated.copy(alpha = 0.6f),
        unfocusedContainerColor = ObsidianDark.copy(alpha = 0.5f),
        focusedTextColor = PlatinumWhite,
        unfocusedTextColor = PlatinumWhite,
        focusedBorderColor = Color.White.copy(alpha = 0.35f),
        unfocusedBorderColor = Color.White.copy(alpha = 0.10f),
        cursorColor = PlatinumWhite
      ),
      modifier = Modifier.fillMaxWidth(),
      singleLine = true
    )

    Spacer(modifier = Modifier.height(14.dp))

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

              Spacer(modifier = Modifier.height(6.dp))

              Text(
                text = chapter.title,
                color = PlatinumWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
              )

              Spacer(modifier = Modifier.height(3.dp))

              Text(
                text = chapter.subtitle,
                color = SilverMedium,
                fontSize = 12.sp,
                maxLines = 2,
                lineHeight = 16.sp
              )
            }

            // Bookmark Button
            IconButton(
              onClick = { onToggleBookmark(chapter.id, isBookmarked) },
              modifier = Modifier.size(34.dp)
            ) {
              Icon(
                imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = if (isBookmarked) GravityAmber else SilverMedium,
                modifier = Modifier.size(18.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          // Footer Info & Progress
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "${chapter.estimatedMinutes} MINS • ${chapter.subtopics.size} SUBTOPICS",
              color = SilverMuted,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              letterSpacing = 0.5.sp
            )

            if (isCompleted) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(5.dp).clip(CircleShape).background(MasteryEmerald))
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                  text = "COMPLETED",
                  color = MasteryEmerald,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 0.5.sp
                )
              }
            } else if (percent > 0f) {
              Text(
                text = "${(percent * 100).toInt()}% MASTERED",
                color = PlatinumWhite,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 0.5.sp
              )
            }
          }
        }
      }
    }
  }
}
