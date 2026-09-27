package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val CalibreyColorScheme = darkColorScheme(
  primary = PlatinumWhite,
  onPrimary = ObsidianVoid,
  primaryContainer = ObsidianElevated,
  onPrimaryContainer = PlatinumWhite,
  secondary = SilverBright,
  onSecondary = ObsidianVoid,
  secondaryContainer = ObsidianElevated,
  onSecondaryContainer = SilverBright,
  tertiary = SilverMedium,
  onTertiary = ObsidianVoid,
  background = ObsidianVoid,
  onBackground = PlatinumWhite,
  surface = ObsidianDark,
  onSurface = PlatinumWhite,
  surfaceVariant = ObsidianElevated,
  onSurfaceVariant = SilverBright,
  outline = ObsidianBorder,
  outlineVariant = GlassBorder,
)

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit
) {
  MaterialTheme(
    colorScheme = CalibreyColorScheme,
    typography = Typography,
    content = content
  )
}

