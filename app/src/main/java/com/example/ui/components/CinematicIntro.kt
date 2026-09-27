package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun CinematicIntro(
  onFinishIntro: () -> Unit
) {
  var startAnimation by remember { mutableStateOf(false) }

  LaunchedEffect(Unit) {
    startAnimation = true
    delay(2200)
    onFinishIntro()
  }

  val alphaAnim by animateFloatAsState(
    targetValue = if (startAnimation) 1f else 0f,
    animationSpec = tween(durationMillis = 900, easing = FastOutSlowInEasing),
    label = "alpha"
  )

  val scaleAnim by animateFloatAsState(
    targetValue = if (startAnimation) 1f else 0.85f,
    animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
    label = "scale"
  )

  val infiniteTransition = rememberInfiniteTransition(label = "pulse")
  val pulseScale by infiniteTransition.animateFloat(
    initialValue = 1f,
    targetValue = 1.05f,
    animationSpec = infiniteRepeatable(
      animation = tween(1200, easing = FastOutSlowInEasing),
      repeatMode = RepeatMode.Reverse
    ),
    label = "pulseScale"
  )

  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(ObsidianVoid),
    contentAlignment = Alignment.Center
  ) {
    // Subtle radial glow
    Box(
      modifier = Modifier
        .size(320.dp)
        .scale(pulseScale)
        .background(
          Brush.radialGradient(
            listOf(Color.White.copy(alpha = 0.08f), Color.Transparent)
          )
        )
    )

    Column(
      modifier = Modifier
        .scale(scaleAnim)
        .alpha(alphaAnim),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Emblem
      Box(
        modifier = Modifier
          .size(90.dp)
          .clip(CircleShape)
          .background(
            Brush.verticalGradient(
              listOf(Color.White.copy(alpha = 0.25f), Color.White.copy(alpha = 0.05f))
            )
          )
          .border(1.5.dp, Color.White.copy(alpha = 0.4f), CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Text(
          text = "C",
          color = PlatinumWhite,
          fontSize = 42.sp,
          fontWeight = FontWeight.Black
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      Text(
        text = "CALIBREY",
        color = PlatinumWhite,
        fontSize = 28.sp,
        fontWeight = FontWeight.Black,
        letterSpacing = 6.sp
      )

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = "INTELLIGENT NCERT OPERATING SYSTEM",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 2.sp
      )

      Spacer(modifier = Modifier.height(48.dp))

      // Minimalist loading bar
      Box(
        modifier = Modifier
          .width(160.dp)
          .height(2.dp)
          .background(Color.White.copy(alpha = 0.15f))
      ) {
        val loadProgress by animateFloatAsState(
          targetValue = if (startAnimation) 1f else 0f,
          animationSpec = tween(1800, easing = LinearEasing),
          label = "load"
        )
        Box(
          modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(loadProgress)
            .background(PlatinumWhite)
        )
      }
    }
  }
}
