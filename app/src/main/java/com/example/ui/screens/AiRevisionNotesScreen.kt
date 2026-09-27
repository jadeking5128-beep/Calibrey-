package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.model.NcertClass
import com.example.model.NoteType
import com.example.model.SavedNote
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun AiRevisionNotesScreen(
  ncertClass: NcertClass,
  initialChapterTitle: String?,
  savedNotes: List<SavedNote>,
  onBackClick: () -> Unit,
  onGenerateNotes: (chapterTitle: String, noteType: NoteType) -> Unit,
  onSaveNote: (chapterTitle: String, noteType: NoteType, content: String) -> Unit,
  onDeleteNote: (String) -> Unit,
  generatedContent: String?,
  isGenerating: Boolean,
  modifier: Modifier = Modifier
) {
  val chapters = remember(ncertClass) {
    NcertCurriculumData.getChaptersForSubject(
      if (ncertClass == NcertClass.CLASS_10) "c10_sci" else "c9_sci"
    )
  }

  var selectedChapter by remember {
    mutableStateOf(chapters.find { it.title == initialChapterTitle } ?: chapters.first())
  }
  var selectedNoteType by remember { mutableStateOf(NoteType.SUMMARY) }
  var noteSavedSuccess by remember { mutableStateOf(false) }

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
          text = "GEMINI-POWERED SYNTHESIS",
          color = SilverMedium,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.sp
        )
        Text(
          text = "AI Revision Notes",
          color = PlatinumWhite,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Chapter Selector
    Text(text = "SELECT CHAPTER", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(6.dp))
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(chapters) { ch ->
        val isSelected = selectedChapter.id == ch.id
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.08f))
            .clickable { selectedChapter = ch }
            .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
          Text(
            text = ch.title,
            color = if (isSelected) ObsidianVoid else PlatinumWhite,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(12.dp))

    // Note Type Selector
    Text(text = "SELECT REVISION ARTIFACT TYPE", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(6.dp))
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(NoteType.values()) { type ->
        val isSelected = selectedNoteType == type
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.08f))
            .clickable { selectedNoteType = type }
            .padding(horizontal = 12.dp, vertical = 6.dp)
        ) {
          Text(
            text = type.label,
            color = if (isSelected) ObsidianVoid else PlatinumWhite,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Generate Button
    GlassButton(
      text = if (isGenerating) "Synthesizing with Calibrey AI..." else "GENERATE ${selectedNoteType.label.uppercase()}",
      onClick = {
        noteSavedSuccess = false
        onGenerateNotes(selectedChapter.title, selectedNoteType)
      },
      enabled = !isGenerating,
      modifier = Modifier.fillMaxWidth(),
      icon = Icons.Default.AutoAwesome
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Generated Note Content Viewer
    if (generatedContent != null) {
      GlassCard(modifier = Modifier.fillMaxWidth()) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "${selectedChapter.title} • ${selectedNoteType.label}",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )

          IconButton(
            onClick = {
              onSaveNote(selectedChapter.title, selectedNoteType, generatedContent)
              noteSavedSuccess = true
            },
            modifier = Modifier.size(32.dp)
          ) {
            Icon(
              imageVector = if (noteSavedSuccess) Icons.Default.Check else Icons.Default.BookmarkAdd,
              contentDescription = "Save Note",
              tint = if (noteSavedSuccess) MasteryEmerald else PlatinumWhite
            )
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
          text = generatedContent,
          color = PlatinumWhite,
          fontSize = 13.sp,
          lineHeight = 19.sp
        )
      }
      Spacer(modifier = Modifier.height(14.dp))
    }

    // Saved Notes Library
    if (savedNotes.isNotEmpty()) {
      Text(
        text = "SAVED REVISION LIBRARY (${savedNotes.size})",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(8.dp))

      savedNotes.forEach { note ->
        GlassCard(modifier = Modifier.fillMaxWidth()) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
          ) {
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "${note.chapterTitle} • ${note.noteType.label}",
                color = PlatinumWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = note.content.take(120) + "...",
                color = SilverMedium,
                fontSize = 11.sp
              )
            }
            IconButton(
              onClick = { onDeleteNote(note.id) },
              modifier = Modifier.size(32.dp)
            ) {
              Icon(Icons.Default.DeleteOutline, contentDescription = "Delete", tint = SilverMuted)
            }
          }
        }
        Spacer(modifier = Modifier.height(8.dp))
      }
    }

    Spacer(modifier = Modifier.height(80.dp))
  }
}
