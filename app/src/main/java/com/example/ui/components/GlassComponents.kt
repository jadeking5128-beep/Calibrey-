package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

/**
 * Premium Apple-Grade Liquid Glass Card
 * Features a deep vitreous obsidian surface, multi-stop diagonal specular sheen,
 * and a directional light border with top specular hairline highlight.
 */
@Composable
fun GlassCard(
  modifier: Modifier = Modifier,
  cornerRadius: Dp = 20.dp,
  borderAlpha: Float = 0.22f,
  backgroundAlpha: Float = 0.08f,
  onClick: (() -> Unit)? = null,
  content: @Composable ColumnScope.() -> Unit
) {
  val shape = RoundedCornerShape(cornerRadius)
  val interactionSource = remember { MutableInteractionSource() }

  val baseModifier = if (onClick != null) {
    modifier
      .clip(shape)
      .clickable(
        interactionSource = interactionSource,
        indication = ripple(color = PlatinumWhite.copy(alpha = 0.14f)),
        onClick = onClick
      )
  } else {
    modifier.clip(shape)
  }

  Column(
    modifier = baseModifier
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(
            ObsidianElevated.copy(alpha = 0.90f),
            ObsidianDark.copy(alpha = 0.98f)
          )
        )
      )
      .background(
        brush = Brush.linearGradient(
          colors = listOf(
            Color.White.copy(alpha = backgroundAlpha + 0.06f),
            Color.White.copy(alpha = backgroundAlpha * 0.35f),
            Color.White.copy(alpha = 0.01f)
          )
        )
      )
      .border(
        BorderStroke(
          1.dp,
          Brush.verticalGradient(
            colors = listOf(
              Color.White.copy(alpha = borderAlpha + 0.20f),
              Color.White.copy(alpha = borderAlpha * 0.5f),
              Color.White.copy(alpha = 0.04f)
            )
          )
        ),
        shape = shape
      )
      .padding(18.dp),
    content = content
  )
}

/**
 * Liquid Glass Status Pill
 * Minimalist geometric indicator replacing emojis with precision OS signals.
 */
@Composable
fun GlassStatusPill(
  label: String,
  accentColor: Color = PlatinumWhite,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .clip(RoundedCornerShape(20.dp))
      .background(accentColor.copy(alpha = 0.08f))
      .border(
        BorderStroke(1.dp, accentColor.copy(alpha = 0.25f)),
        shape = RoundedCornerShape(20.dp)
      )
      .padding(horizontal = 9.dp, vertical = 3.5.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Box(
      modifier = Modifier
        .size(5.dp)
        .clip(CircleShape)
        .background(accentColor)
    )
    Spacer(modifier = Modifier.width(6.dp))
    Text(
      text = label.uppercase(),
      color = accentColor,
      fontSize = 9.5.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 0.8.sp
    )
  }
}

/**
 * High-End Segmented Control for Monochrome Liquid Glass
 */
@Composable
fun GlassSegmentedControl(
  options: List<String>,
  selectedIndex: Int,
  onSelect: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(24.dp))
      .background(Color.White.copy(alpha = 0.06f))
      .border(
        BorderStroke(1.dp, Color.White.copy(alpha = 0.12f)),
        RoundedCornerShape(24.dp)
      )
      .padding(4.dp),
    horizontalArrangement = Arrangement.spacedBy(4.dp)
  ) {
    options.forEachIndexed { index, option ->
      val isSelected = selectedIndex == index
      val bg = if (isSelected) PlatinumWhite else Color.Transparent
      val textCol = if (isSelected) ObsidianPure else SilverMedium

      Box(
        modifier = Modifier
          .weight(1f)
          .clip(RoundedCornerShape(20.dp))
          .background(bg)
          .clickable { onSelect(index) }
          .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = option.uppercase(),
          color = textCol,
          fontSize = 11.sp,
          fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
          letterSpacing = 0.5.sp
        )
      }
    }
  }
}

/**
 * Minimalist High-End Glass Button
 */
@Composable
fun GlassButton(
  text: String,
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  isPrimary: Boolean = true,
  icon: ImageVector? = null,
  enabled: Boolean = true
) {
  val shape = RoundedCornerShape(14.dp)
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
        contentColor = ObsidianPure,
        disabledContainerColor = CharcoalGray,
        disabledContentColor = SilverMuted
      )
    } else {
      ButtonDefaults.buttonColors(
        containerColor = Color.White.copy(alpha = 0.08f),
        contentColor = PlatinumWhite,
        disabledContainerColor = Color.White.copy(alpha = 0.02f),
        disabledContentColor = SilverMuted
      )
    },
    border = if (!isPrimary) BorderStroke(
      1.dp,
      Brush.verticalGradient(
        listOf(Color.White.copy(alpha = 0.28f), Color.White.copy(alpha = 0.06f))
      )
    ) else null,
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
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
      }
      Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.4.sp
      )
    }
  }
}

/**
 * World-Class Top Bar
 */
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
      .background(ObsidianPure.copy(alpha = 0.92f))
      .border(
        BorderStroke(
          0.5.dp,
          Brush.verticalGradient(
            listOf(Color.Transparent, Color.White.copy(alpha = 0.08f))
          )
        )
      )
      .padding(horizontal = 18.dp, vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    // Brand Monogram & Sub-title
    Row(verticalAlignment = Alignment.CenterVertically) {
      Box(
        modifier = Modifier
          .size(34.dp)
          .clip(RoundedCornerShape(10.dp))
          .background(
            Brush.linearGradient(
              listOf(Color.White.copy(alpha = 0.20f), Color.White.copy(alpha = 0.04f))
            )
          )
          .border(
            1.dp,
            Brush.verticalGradient(
              listOf(Color.White.copy(alpha = 0.40f), Color.White.copy(alpha = 0.08f))
            ),
            RoundedCornerShape(10.dp)
          ),
        contentAlignment = Alignment.Center
      ) {
        Text("C", color = PlatinumWhite, fontWeight = FontWeight.Black, fontSize = 16.sp)
      }
      Spacer(modifier = Modifier.width(10.dp))
      Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text(
            text = "CALIBREY",
            color = PlatinumWhite,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
          )
          Spacer(modifier = Modifier.width(6.dp))
          Box(
            modifier = Modifier
              .size(5.dp)
              .clip(CircleShape)
              .background(MasteryEmerald)
          )
        }
        Text(
          text = title.uppercase(),
          color = SilverMedium,
          fontSize = 10.sp,
          letterSpacing = 1.sp
        )
      }
    }

    // Indicator Pills: Streak, GP & Avatar Profile
    Row(verticalAlignment = Alignment.CenterVertically) {
      // Streak Pill
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(Color.White.copy(alpha = 0.06f))
          .border(1.dp, Color.White.copy(alpha = 0.14f), RoundedCornerShape(20.dp))
          .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Box(
          modifier = Modifier
            .size(5.dp)
            .clip(CircleShape)
            .background(GravityAmber)
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
          text = "${streakDays}D",
          color = PlatinumWhite,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      // Gravity Points Pill
      Row(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(GravityAmber.copy(alpha = 0.12f))
          .border(1.dp, GravityAmber.copy(alpha = 0.28f), RoundedCornerShape(20.dp))
          .padding(horizontal = 9.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = "$gravityPoints",
          color = PlatinumWhite,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(3.dp))
        Text(
          text = "GP",
          color = GravityAmber,
          fontSize = 9.sp,
          fontWeight = FontWeight.Black
        )
      }

      Spacer(modifier = Modifier.width(8.dp))

      // Profile Button
      Box(
        modifier = Modifier
          .size(34.dp)
          .clip(CircleShape)
          .background(Color.White.copy(alpha = 0.10f))
          .border(1.dp, Color.White.copy(alpha = 0.25f), CircleShape)
          .clickable(onClick = onProfileClick),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.Person,
          contentDescription = "Profile",
          tint = PlatinumWhite,
          modifier = Modifier.size(16.dp)
        )
      }
    }
  }
}

enum class NavigationTab(val label: String, val icon: ImageVector) {
  DASHBOARD("Hub", Icons.Default.Dashboard),
  LEARN("Curriculum", Icons.AutoMirrored.Filled.MenuBook),
  BRAIN("AI Tutor", Icons.Default.AutoAwesome),
  REVISE("Revision", Icons.Default.Loop),
  SOCIAL("Community", Icons.Default.Groups)
}

/**
 * Floating Liquid Glass Dock
 */
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
      .padding(horizontal = 20.dp, vertical = 10.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(32.dp))
        .background(ObsidianElevated.copy(alpha = 0.95f))
        .background(
          Brush.linearGradient(
            listOf(Color.White.copy(alpha = 0.08f), Color.White.copy(alpha = 0.01f))
          )
        )
        .border(
          BorderStroke(
            1.dp,
            Brush.verticalGradient(
              listOf(Color.White.copy(alpha = 0.32f), Color.White.copy(alpha = 0.06f))
            )
          ),
          shape = RoundedCornerShape(32.dp)
        )
        .padding(horizontal = 6.dp, vertical = 6.dp),
      horizontalArrangement = Arrangement.SpaceAround,
      verticalAlignment = Alignment.CenterVertically
    ) {
      NavigationTab.values().forEach { tab ->
        val isSelected = tab == currentTab
        val background = if (isSelected) Color.White.copy(alpha = 0.12f) else Color.Transparent
        val textColor = if (isSelected) PlatinumWhite else SilverMuted
        val iconColor = if (isSelected) PlatinumWhite else SilverMedium

        Column(
          modifier = Modifier
            .clip(RoundedCornerShape(22.dp))
            .background(background)
            .clickable { onTabSelected(tab) }
            .padding(horizontal = 14.dp, vertical = 8.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          Icon(
            imageVector = tab.icon,
            contentDescription = tab.label,
            tint = iconColor,
            modifier = Modifier.size(19.dp)
          )
          Spacer(modifier = Modifier.height(3.dp))
          Text(
            text = tab.label,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
          )
          if (isSelected) {
            Spacer(modifier = Modifier.height(2.dp))
            Box(
              modifier = Modifier
                .width(12.dp)
                .height(2.dp)
                .clip(CircleShape)
                .background(PlatinumWhite)
            )
          }
        }
      }
    }
  }
}
