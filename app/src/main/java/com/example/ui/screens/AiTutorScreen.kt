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
import com.example.ai.AiMessage
import com.example.ai.AiTutorMode
import com.example.model.UserProfile
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun AiTutorScreen(
  userProfile: UserProfile,
  messages: List<AiMessage>,
  isLoading: Boolean,
  onSendMessage: (query: String, mode: AiTutorMode) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedMode by remember { mutableStateOf(AiTutorMode.CONCEPT_BREAKDOWN) }
  var inputQuery by remember { mutableStateOf("") }

  val promptChips = listOf(
    "Derive the mirror formula with sign conventions",
    "Why is respiration considered exothermic in NCERT?",
    "Explain Ohm's Law and factors affecting resistance",
    "How does the nephron filter blood in the kidney?",
    "Balance: Fe + H2O -> Fe3O4 + H2 step-by-step"
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    // Mode Switcher Pills
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(AiTutorMode.values()) { mode ->
        val isSelected = selectedMode == mode
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.08f))
            .border(1.dp, if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
            .clickable { selectedMode = mode }
            .padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
          Text(
            text = mode.title,
            color = if (isSelected) ObsidianVoid else PlatinumWhite,
            fontSize = 12.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Suggested Prompt Chips
    LazyRow(
      horizontalArrangement = Arrangement.spacedBy(6.dp),
      modifier = Modifier.fillMaxWidth()
    ) {
      items(promptChips) { chip ->
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(Color.White.copy(alpha = 0.04f))
            .border(0.5.dp, Color.White.copy(alpha = 0.12f), RoundedCornerShape(14.dp))
            .clickable { inputQuery = chip }
            .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
          Text(
            text = chip,
            color = SilverBright,
            fontSize = 11.sp,
            maxLines = 1
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Messages Conversation List
    LazyColumn(
      modifier = Modifier
        .weight(1f)
        .fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
      items(messages) { msg ->
        if (msg.isUser) {
          // User Message
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            Box(
              modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(PlatinumWhite)
                .padding(12.dp)
            ) {
              Text(
                text = msg.content,
                color = ObsidianVoid,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }
        } else {
          // AI Tutor Response
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
          ) {
            GlassCard(
              modifier = Modifier.fillMaxWidth(),
              backgroundAlpha = 0.07f
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                  modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.15f)),
                  contentAlignment = Alignment.Center
                ) {
                  Text("🧠", fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "CALIBREY AI • ${selectedMode.title.uppercase()}",
                  color = SilverMedium,
                  fontSize = 10.sp,
                  fontWeight = FontWeight.Bold,
                  letterSpacing = 1.sp
                )
              }

              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = msg.content,
                color = PlatinumWhite,
                fontSize = 13.sp,
                lineHeight = 19.sp
              )
            }
          }
        }
      }

      if (isLoading) {
        item {
          Row(verticalAlignment = Alignment.CenterVertically) {
            CircularProgressIndicator(
              modifier = Modifier.size(16.dp),
              color = PlatinumWhite,
              strokeWidth = 2.dp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Calibrey AI is synthesizing NCERT response...", color = SilverMedium, fontSize = 12.sp)
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Chat Input Bar
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(24.dp))
        .background(ObsidianDark)
        .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(24.dp))
        .padding(horizontal = 14.dp, vertical = 4.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      TextField(
        value = inputQuery,
        onValueChange = { inputQuery = it },
        placeholder = { Text("Ask your doubt or concept question...", color = SilverMuted, fontSize = 13.sp) },
        colors = TextFieldDefaults.colors(
          focusedContainerColor = Color.Transparent,
          unfocusedContainerColor = Color.Transparent,
          focusedTextColor = PlatinumWhite,
          unfocusedTextColor = PlatinumWhite,
          focusedIndicatorColor = Color.Transparent,
          unfocusedIndicatorColor = Color.Transparent,
          cursorColor = PlatinumWhite
        ),
        modifier = Modifier.weight(1f)
      )

      IconButton(
        onClick = {
          if (inputQuery.isNotBlank() && !isLoading) {
            val q = inputQuery.trim()
            inputQuery = ""
            onSendMessage(q, selectedMode)
          }
        },
        enabled = inputQuery.isNotBlank() && !isLoading
      ) {
        Icon(
          imageVector = Icons.Default.Send,
          contentDescription = "Send",
          tint = if (inputQuery.isNotBlank()) PlatinumWhite else SilverMuted
        )
      }
    }

    Spacer(modifier = Modifier.height(72.dp))
  }
}
