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
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.NcertClass
import com.example.model.UserRole
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.components.GlassSegmentedControl
import com.example.ui.theme.*

@Composable
fun OnboardingScreen(
  onComplete: (name: String, role: UserRole, ncertClass: NcertClass, avatarId: Int, apiKey: String) -> Unit,
  modifier: Modifier = Modifier
) {
  var name by remember { mutableStateOf("") }
  var roleIndex by remember { mutableStateOf(0) }
  val selectedRole = if (roleIndex == 0) UserRole.STUDENT else UserRole.TEACHER
  var selectedClass by remember { mutableStateOf(NcertClass.CLASS_10) }
  var selectedAvatarId by remember { mutableStateOf(1) }
  var apiKey by remember { mutableStateOf("") }
  var errorMessage by remember { mutableStateOf<String?>(null) }

  // Academic Insignias (monograms instead of emojis)
  val insignias = listOf(
    1 to "SC", // Science Scholar
    2 to "MT", // Math Theorist
    3 to "PH", // Physics Core
    4 to "CH", // Chemistry Lab
    5 to "BI", // Biology Bio
    6 to "CB"  // Calibrey Fellow
  )

  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianPure)
      .windowInsetsPadding(WindowInsets.safeDrawing)
      .verticalScroll(rememberScrollState())
      .padding(horizontal = 20.dp, vertical = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Spacer(modifier = Modifier.height(12.dp))

    // Calibrey Brand Emblem
    Box(
      modifier = Modifier
        .size(54.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(
          Brush.linearGradient(
            listOf(Color.White.copy(alpha = 0.22f), Color.White.copy(alpha = 0.05f))
          )
        )
        .border(
          1.dp,
          Brush.verticalGradient(
            listOf(Color.White.copy(alpha = 0.45f), Color.White.copy(alpha = 0.10f))
          ),
          RoundedCornerShape(16.dp)
        ),
      contentAlignment = Alignment.Center
    ) {
      Text("C", color = PlatinumWhite, fontSize = 26.sp, fontWeight = FontWeight.Black)
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = "CALIBREY ACADEMIC OS",
      color = SilverMedium,
      fontSize = 10.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 2.sp
    )

    Spacer(modifier = Modifier.height(2.dp))

    Text(
      text = "Initialize Student Profile",
      color = PlatinumWhite,
      fontSize = 22.sp,
      fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(6.dp))

    Text(
      text = "Sign in with your academic identity to track NCERT mastery",
      color = SilverMedium,
      fontSize = 12.sp
    )

    Spacer(modifier = Modifier.height(20.dp))

    // Profile Setup Glass Container
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      // 1. Academic Role Switcher
      Text(
        text = "ACADEMIC ROLE",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(6.dp))

      GlassSegmentedControl(
        options = listOf("Student", "Educator / Mentor"),
        selectedIndex = roleIndex,
        onSelect = { roleIndex = it }
      )

      Spacer(modifier = Modifier.height(16.dp))

      // 2. Full Name
      Text(
        text = if (selectedRole == UserRole.STUDENT) "STUDENT FULL NAME" else "EDUCATOR FULL NAME",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(6.dp))

      OutlinedTextField(
        value = name,
        onValueChange = {
          name = it
          errorMessage = null
        },
        placeholder = { Text("Enter your full academic name...", color = SilverMuted, fontSize = 13.sp) },
        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = PlatinumWhite, modifier = Modifier.size(18.dp)) },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedContainerColor = Color.White.copy(alpha = 0.06f),
          unfocusedContainerColor = Color.White.copy(alpha = 0.03f),
          focusedTextColor = PlatinumWhite,
          unfocusedTextColor = PlatinumWhite,
          focusedBorderColor = PlatinumWhite.copy(alpha = 0.4f),
          unfocusedBorderColor = Color.White.copy(alpha = 0.12f),
          cursorColor = PlatinumWhite
        ),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )

      Spacer(modifier = Modifier.height(16.dp))

      // 3. Choose Insignia Emblem
      Text(
        text = "SELECT ACADEMIC INSIGNIA",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(8.dp))

      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        insignias.forEach { (id, code) ->
          val isSelected = selectedAvatarId == id
          Box(
            modifier = Modifier
              .size(44.dp)
              .clip(CircleShape)
              .background(if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.06f))
              .border(
                1.dp,
                if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.15f),
                CircleShape
              )
              .clickable { selectedAvatarId = id },
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = code,
              fontSize = 13.sp,
              fontWeight = FontWeight.Bold,
              color = if (isSelected) ObsidianPure else PlatinumWhite
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // 4. NCERT Grade Level
      Text(
        text = "NCERT CURRICULUM GRADE",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(8.dp))

      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        NcertClass.values().forEach { ncertClass ->
          val isSelected = selectedClass == ncertClass
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(10.dp))
              .background(if (isSelected) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.03f))
              .border(
                1.dp,
                if (isSelected) PlatinumWhite else Color.White.copy(alpha = 0.08f),
                RoundedCornerShape(10.dp)
              )
              .clickable { selectedClass = ncertClass }
              .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(
                imageVector = Icons.Default.School,
                contentDescription = null,
                tint = if (isSelected) PlatinumWhite else SilverMedium,
                modifier = Modifier.size(16.dp)
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text(
                text = ncertClass.displayName,
                color = if (isSelected) PlatinumWhite else SilverBright,
                fontSize = 13.sp,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
              )
            }
            if (isSelected) {
              Icon(
                Icons.Default.Check,
                contentDescription = "Selected",
                tint = PlatinumWhite,
                modifier = Modifier.size(16.dp)
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      // 5. Optional Gemini API Key
      Text(
        text = "AI TUTOR CREDENTIALS (OPTIONAL)",
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "Leave empty to use built-in NCERT educational AI tier.",
        color = SilverMuted,
        fontSize = 11.sp
      )
      Spacer(modifier = Modifier.height(6.dp))

      OutlinedTextField(
        value = apiKey,
        onValueChange = { apiKey = it },
        placeholder = { Text("Gemini API key (optional)...", color = SilverMuted, fontSize = 13.sp) },
        leadingIcon = { Icon(Icons.Default.Key, contentDescription = null, tint = SilverMedium, modifier = Modifier.size(18.dp)) },
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
          focusedContainerColor = Color.White.copy(alpha = 0.06f),
          unfocusedContainerColor = Color.White.copy(alpha = 0.03f),
          focusedTextColor = PlatinumWhite,
          unfocusedTextColor = PlatinumWhite,
          focusedBorderColor = Color.White.copy(alpha = 0.35f),
          unfocusedBorderColor = Color.White.copy(alpha = 0.10f),
          cursorColor = PlatinumWhite
        ),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
      )
    }

    if (errorMessage != null) {
      Spacer(modifier = Modifier.height(10.dp))
      Text(
        text = errorMessage!!,
        color = UrgentRose,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Complete Authentication Button
    GlassButton(
      text = "AUTHENTICATE & ENTER CALIBREY (+100 GP)",
      onClick = {
        if (name.trim().isBlank()) {
          errorMessage = "Please enter your academic name to continue."
        } else {
          onComplete(
            name.trim(),
            selectedRole,
            selectedClass,
            selectedAvatarId,
            apiKey.trim()
          )
        }
      },
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))

    // Security Assurance Notice
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Icon(Icons.Default.Shield, contentDescription = null, tint = SilverMuted, modifier = Modifier.size(13.dp))
      Spacer(modifier = Modifier.width(6.dp))
      Text(
        text = "Encrypted local SQLite storage • NCERT aligned syllabus",
        color = SilverMuted,
        fontSize = 10.5.sp
      )
    }

    Spacer(modifier = Modifier.height(24.dp))
  }
}
