package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.window.Dialog
import com.example.model.Achievement
import com.example.model.NcertClass
import com.example.model.UserProfile
import com.example.model.UserRole
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun ProfileDialog(
  userProfile: UserProfile,
  onDismiss: () -> Unit,
  onSaveProfile: (name: String, role: UserRole, ncertClass: NcertClass, avatarId: Int, apiKey: String) -> Unit
) {
  var name by remember { mutableStateOf(userProfile.fullName) }
  var selectedClass by remember { mutableStateOf(userProfile.ncertClass) }
  var selectedAvatarId by remember { mutableStateOf(userProfile.avatarId) }
  var apiKey by remember { mutableStateOf(userProfile.geminiApiKey) }
  var activeTab by remember { mutableStateOf("Profile") } // "Profile" or "Achievements"

  val avatars = listOf("👨‍🎓", "👩‍🎓", "🧑‍🔬", "👩‍💻", "🧑‍🏫", "🚀")

  val achievements = listOf(
    Achievement("a1", "Quantum Leap", "Earned your first 1,000 Gravity Points", "⚡", true, "Sept 2026", 100),
    Achievement("a2", "Solar Streak", "Maintained a 7-day study streak", "🔥", true, "Sept 2026", 150),
    Achievement("a3", "Optics Virtuoso", "Perfect 100% score on Light Reflection quiz", "🔬", true, "Sept 2026", 200),
    Achievement("a4", "Formula Alchemist", "Generated 5 AI master revision sheets", "🧪", true, "Sept 2026", 120),
    Achievement("a5", "Night Owl Scholar", "Completed a study session past 10 PM", "🦉", false, "", 80),
    Achievement("a6", "Singularity Master", "Attain 10,000+ Gravity Points", "🌌", false, "", 500)
  )

  Dialog(onDismissRequest = onDismiss) {
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = ObsidianDark,
      border = androidx.compose.foundation.BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)),
      modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 620.dp)
    ) {
      Column(
        modifier = Modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
          .padding(20.dp)
      ) {
        // Dialog Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = "PROFILE & IDENTITY",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp
          )
          IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
            Icon(Icons.Default.Close, contentDescription = "Close", tint = PlatinumWhite)
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Switcher Tabs
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White.copy(alpha = 0.05f))
            .padding(4.dp)
        ) {
          listOf("Profile", "Achievements").forEach { tab ->
            val isSelected = activeTab == tab
            Box(
              modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(if (isSelected) PlatinumWhite else Color.Transparent)
                .clickable { activeTab = tab }
                .padding(vertical = 8.dp),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = tab,
                color = if (isSelected) ObsidianVoid else SilverMedium,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
              )
            }
          }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (activeTab == "Profile") {
          // Avatar Row
          Text(text = "AVATAR", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold)
          Spacer(modifier = Modifier.height(6.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            avatars.forEachIndexed { index, av ->
              val isSelected = selectedAvatarId == index + 1
              Box(
                modifier = Modifier
                  .size(40.dp)
                  .clip(CircleShape)
                  .background(if (isSelected) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.05f))
                  .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.15f),
                    shape = CircleShape
                  )
                  .clickable { selectedAvatarId = index + 1 },
                contentAlignment = Alignment.Center
              ) {
                Text(text = av, fontSize = 20.sp)
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Name Input
          OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Student Name") },
            colors = OutlinedTextFieldDefaults.colors(
              focusedTextColor = PlatinumWhite,
              unfocusedTextColor = PlatinumWhite,
              focusedBorderColor = PlatinumWhite,
              unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
              cursorColor = PlatinumWhite
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Academic Class Switcher
          Text(
            text = "SWITCH NCERT CLASS (UPDATES ENTIRE CURRICULUM)",
            color = SilverMedium,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(6.dp))

          Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
            NcertClass.values().forEach { nClass ->
              val isSelected = selectedClass == nClass
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(8.dp))
                  .background(if (isSelected) Color.White.copy(alpha = 0.18f) else Color.White.copy(alpha = 0.04f))
                  .border(1.dp, if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.1f), RoundedCornerShape(8.dp))
                .clickable { selectedClass = nClass }
                .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = nClass.displayName,
                  color = if (isSelected) PlatinumWhite else SilverBright,
                  fontSize = 13.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
                if (isSelected) {
                  Icon(Icons.Default.Check, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(16.dp))
                }
              }
            }
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Gemini API Key
          Text(text = "CUSTOM GEMINI API KEY", color = SilverMedium, fontSize = 10.sp, fontWeight = FontWeight.Bold)
          Spacer(modifier = Modifier.height(6.dp))
          OutlinedTextField(
            value = apiKey,
            onValueChange = { apiKey = it },
            placeholder = { Text("AIzaSy... (uses studio default if empty)") },
            colors = OutlinedTextFieldDefaults.colors(
              focusedTextColor = PlatinumWhite,
              unfocusedTextColor = PlatinumWhite,
              focusedBorderColor = PlatinumWhite,
              unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
              cursorColor = PlatinumWhite
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
          )

          Spacer(modifier = Modifier.height(18.dp))

          // Save Button
          GlassButton(
            text = "SAVE CHANGES",
            onClick = {
              onSaveProfile(name.trim(), userProfile.role, selectedClass, selectedAvatarId, apiKey.trim())
              onDismiss()
            },
            modifier = Modifier.fillMaxWidth()
          )
        } else {
          // Achievements Tab
          Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            achievements.forEach { ach ->
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(10.dp))
                  .background(if (ach.isUnlocked) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.02f))
                  .border(0.5.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(10.dp))
                  .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(text = ach.iconEmoji, fontSize = 24.sp)
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = ach.title,
                    color = if (ach.isUnlocked) PlatinumWhite else SilverMuted,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                  )
                  Text(
                    text = ach.description,
                    color = SilverMuted,
                    fontSize = 11.sp
                  )
                }
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(GravityAmber.copy(alpha = 0.15f))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(text = "+${ach.rewardGp} GP", color = GravityAmber, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          }
        }
      }
    }
  }
}
