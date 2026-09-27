package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

@Composable
fun GlassCard(
  modifier: Modifier = Modifier,
  cornerRadius: Dp = 16.dp,
  borderAlpha: Float = 0.15f,
  backgroundAlpha: Float = 0.08f,
  onClick: (() -> Unit)? = null,
  content: @Composable ColumnScope.() -> Unit
) {
  val shape = RoundedCornerShape(cornerRadius)
  val baseModifier = if (onClick != null) {
    modifier
      .clip(shape)
      .clickable(onClick = onClick)
  } else {
    modifier.clip(shape)
  }

  Column(
    modifier = baseModifier
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(
            Color.White.copy(alpha = backgroundAlpha + 0.04f),
            Color.White.copy(alpha = backgroundAlpha)
          )
        )
      )
      .border(
        BorderStroke(
          1.dp,
          Brush.verticalGradient(
            colors = listOf(
              Color.White.copy(alpha = borderAlpha + 0.15f),
              Color.White.copy(alpha = borderAlpha * 0.5f)
            )
          )
        ),
        shape = shape
      )
      .padding(16.dp),
    content = content
  )
}

@Composable
fun GlassButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  isPrimary: Boolean = true,
  icon: ImageVector? = null,
  enabled: Boolean = true
) {
  val shape = RoundedCornerShape(12.dp)
  Button(
    onClick = onClick,
    enabled = enabled,
    modifier = modifier
      .defaultMinSize(minHeight = 48.dp)
      .clip(shape),
    shape = shape,
    colors = if (isPrimary) {
      ButtonDefaults.buttonColors(
        containerColor = PlatinumWhite,
        contentColor = ObsidianVoid,
        disabledContainerColor = CharcoalGray,
        disabledContentColor = SilverMuted
      )
    } else {
      ButtonDefaults.buttonColors(
        containerColor = Color.White.copy(alpha = 0.1f),
        contentColor = PlatinumWhite,
        disabledContainerColor = Color.White.copy(alpha = 0.04f),
        disabledContentColor = SilverMuted
      )
    },
    border = if (!isPrimary) BorderStroke(1.dp, Color.White.copy(alpha = 0.2f)) else null,
    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
  ) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      if (icon != null) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
      }
      Text(
        text = text,
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
      )
    }
  }
}

@Composable
fun GlassTopBar(
  title: String,
  gravityPoints: Int,
  streakDays: Int,
  avatarId: Int,
  onProfileClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .background(ObsidianVoid.copy(alpha = 0.85f))
      .padding(horizontal = 16.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    // Brand & Title
    Row(verticalAlignment = Alignment.CenterVertically) {
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(
            Brush.radialGradient(
              listOf(Color.White.copy(alpha = 0.25f), Color.White.copy(alpha = 0.05f))
            )
          )
          .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Text("C", color = PlatinumWhite, fontWeight = FontWeight.Black, fontSize = 18.sp)
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Text(
          text = "CALIBREY",
          color = PlatinumWhite,
          fontSize = 13.sp,
          fontWeight = FontWeight.Bold,
          letterSpacing = 1.5.sp
        )
        Text(
          text = title,
          color = SilverMedium,
          fontSize = 12.sp
        )
      }
    }

    // Indicators: Streak, GP & Avatar
    Row(verticalAlignment = Alignment.CenterVertically) {
      // Streak Badge
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White.copy(alpha = 0.08f))
          .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(20.dp))
          .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text("🔥", fontSize = 12.sp)
        Spacer(modifier = Modifier.width(3.dp))
        Text(
          text = "$streakDays",
          color = PlatinumWhite,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      // Gravity Points Badge
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White.copy(alpha = 0.12f))
          .border(1.dp, Color.White.copy(alpha = 0.25f), RoundedCornerShape(20.dp))
          .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "GP",
          color = GravityAmber,
          fontSize = 11.sp,
          fontWeight = FontWeight.Black
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
          text = "$gravityPoints",
          color = PlatinumWhite,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      // Profile Button
      Box(
        modifier = Modifier
          .size(36.dp)
          .clip(CircleShape)
          .background(Color.White.copy(alpha = 0.15f))
          .border(1.dp, Color.White.copy(alpha = 0.3f), CircleShape)
          .clickable(onClick = onProfileClick),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "👤",
          fontSize = 16.sp
        )
      }
    }
  }
}

enum class NavigationTab(val label: String, val icon: ImageVector) {
  DASHBOARD("Hub", Icons.Default.Dashboard),
  LEARN("Curriculum", Icons.Default.MenuBook),
  BRAIN("AI Tutor", Icons.Default.AutoAwesome),
  REVISE("Revision", Icons.Default.Loop),
  SOCIAL("Community", Icons.Default.People)
}

@Composable
fun GlassBottomNavigation(
  currentTab: NavigationTab,
  onTabSelected: (NavigationTab) -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .windowInsetsPadding(WindowInsets.navigationBars)
      .padding(horizontal = 16.dp, vertical = 8.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(28.dp))
        .background(ObsidianDark.copy(alpha = 0.92f))
        .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(28.dp))
        .padding(horizontal = 6.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      NavigationTab.values().forEach { tab ->
        val isSelected = tab == currentTab
        val background = if (isSelected) Color.White.copy(alpha = 0.15f) else Color.Transparent
        val textColor = if (isSelected) PlatinumWhite else SilverMuted
        val iconColor = if (isSelected) PlatinumWhite else SilverMedium

        Column(
          modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(background)
            .clickable { onTabSelected(tab) }
            .padding(horizontal = 12.dp, vertical = 6.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = tab.icon,
            contentDescription = tab.label,
            tint = iconColor,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = tab.label,
            color = textColor,
            fontSize = 11.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
          )
        }
      }
    }
  }
}
