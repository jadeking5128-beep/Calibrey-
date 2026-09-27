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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NcertClass
import com.example.model.UserRole
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun OnboardingScreen(
  onComplete: (name: String, role: UserRole, ncertClass: NcertClass, avatarId: Int, apiKey: String) -> Unit,
  modifier: Modifier = Modifier
) {
  var name by remember { mutableStateOf("Aarav Sharma") }
  var selectedRole by remember { mutableStateOf(UserRole.STUDENT) }
  var selectedClass by remember { mutableStateOf(NcertClass.CLASS_10) }
  var selectedAvatarId by remember { mutableStateOf(1) }
  var apiKey by remember { mutableStateOf("") }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  val avatars = listOf("👨‍🎓", "👩‍🎓", "🧑‍🔬", "👩‍💻", "🧑‍🏫", "🚀")

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .windowInsetsPadding(WindowInsets.safeDrawing)
      .verticalScroll(rememberScrollState())
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(20.dp))

    // Header Branding
    Box(
      modifier = Modifier
        .size(60.dp)
        .clip(CircleShape)
        .background(Color.White.copy(alpha = 0.1f))
        .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape),
      contentAlignment = Alignment.Center
    ) {
      Text("C", color = PlatinumWhite, fontSize = 28.sp, fontWeight = FontWeight.Black)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Text(
      text = "WELCOME TO CALIBREY",
      color = SilverMedium,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 2.sp
    )

    Text(
      text = "Setup Your Learning Identity",
      color = PlatinumWhite,
      fontSize = 22.sp,
      fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Profile Card
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = "CHOOSE AVATAR",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(10.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        avatars.forEachIndexed { index, avatar ->
          val isSelected = selectedAvatarId == index + 1
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(if (isSelected) Color.White.copy(alpha = 0.25f) else Color.White.copy(alpha = 0.05f))
              .border(
                width = if (isSelected) 2.dp else 1.dp,
                color = if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.15f),
                shape = CircleShape
              )
              .clickable { selectedAvatarId = index + 1 },
            contentAlignment = Alignment.Center
          ) {
            Text(text = avatar, fontSize = 22.sp)
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Name Input
      OutlinedTextField(
        value = name,
        onValueChange = { name = it },
        label = { Text("Student Full Name") },
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = SilverMedium) },
        colors = OutlinedTextFieldDefaults.colors(
          focusedTextColor = PlatinumWhite,
          unfocusedTextColor = PlatinumWhite,
          focusedBorderColor = PlatinumWhite,
          unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
          focusedLabelColor = PlatinumWhite,
          unfocusedLabelColor = SilverMedium,
          cursorColor = PlatinumWhite
        ),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )

      Spacer(modifier = Modifier.height(18.dp))

      // Role Selection
      Text(
        text = "ACCOUNT TYPE",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(8.dp))
      Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        listOf(UserRole.STUDENT to "Student Learner", UserRole.TEACHER to "Teacher / Mentor").forEach { (role, label) ->
          val isSelected = selectedRole == role
          Box(
            modifier = Modifier
              .weight(1f)
              .clip(RoundedCornerShape(10.dp))
              .background(if (isSelected) Color.White.copy(alpha = 0.18f) else Color.White.copy(alpha = 0.04f))
              .border(1.dp, if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.15f), RoundedCornerShape(10.dp))
              .clickable { selectedRole = role }
              .padding(vertical = 10.dp),
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = label,
              color = if (isSelected) PlatinumWhite else SilverMedium,
              fontSize = 13.sp,
              fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // NCERT Class Selection
      Text(
        text = "NCERT ACADEMIC CLASS",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(8.dp))

      Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        NcertClass.values().forEach { nClass ->
          val isSelected = selectedClass == nClass
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(if (isSelected) Color.White.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.05f))
              .border(1.dp, if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.12f), RoundedCornerShape(10.dp))
              .clickable { selectedClass = nClass }
              .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.School,
                contentDescription = null,
                tint = if (isSelected) PlatinumWhite else SilverMedium,
                modifier = Modifier.size(18.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = nClass.displayName,
                color = if (isSelected) PlatinumWhite else SilverBright,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
              )
            }
            if (isSelected) {
              Icon(Icons.Default.Check, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(18.dp))
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Gemini API Key Input
      Text(
        text = "GEMINI AI FOUNDATION KEY",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Required for Socratic AI tutoring, Doubt Solver, and Revision Notes generation. Leave empty to use pre-configured studio key.",
        color = SilverMuted,
        fontSize = 11.sp
      )
      Spacer(modifier = Modifier.height(8.dp))

      OutlinedTextField(
        value = apiKey,
        onValueChange = { apiKey = it },
        placeholder = { Text("AIzaSy... (or pre-configured)") },
        leadingIcon = { Icon(Icons.Default.Key, contentDescription = null, tint = SilverMedium) },
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
    }

    if (errorMessage != null) {
      Spacer(modifier = Modifier.height(10.dp))
      Text(text = errorMessage!!, color = UrgentRose, fontSize = 12.sp)
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Launch Button
    GlassButton(
      text = "ENTER CALIBREY OS",
      onClick = {
        if (name.isBlank()) {
          errorMessage = "Please enter your name."
        } else {
          onComplete(name.trim(), selectedRole, selectedClass, selectedAvatarId, apiKey.trim())
        }
      },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(20.dp))
  }
}
