package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ui.theme.*

@Composable
fun EmbeddedVideoCard(
  videoId: String,
  title: String,
  duration: String,
  modifier: Modifier = Modifier,
  onPlayClick: (() -> Unit)? = null
) {
  val context = LocalContext.current
  val thumbnailUrl = "https://img.youtube.com/vi/$videoId/hqdefault.jpg"

  GlassCard(
    modifier = modifier.fillMaxWidth(),
    onClick = {
      if (onPlayClick != null) {
        onPlayClick()
      } else {
        val appIntent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoId"))
        try {
          context.startActivity(appIntent)
        } catch (ex: Exception) {
          context.startActivity(webIntent)
        }
      }
    }
  ) {
    // Video Thumbnail Banner
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
        .clip(RoundedCornerShape(12.dp))
        .background(ObsidianDark)
        .border(1.dp, Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
      contentAlignment = Alignment.Center
    ) {
      AsyncImage(
        model = thumbnailUrl,
        contentDescription = title,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
      )

      // Dark Overlay Gradient
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(
            Brush.verticalGradient(
              listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
            )
          )
      )

      // Play Button with Liquid Glow
      Box(
        modifier = Modifier
          .size(54.dp)
          .clip(CircleShape)
          .background(PlatinumWhite.copy(alpha = 0.9f))
          .border(2.dp, Color.White, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.PlayArrow,
          contentDescription = "Play Video",
          tint = ObsidianVoid,
          modifier = Modifier.size(32.dp)
        )
      }

      // Duration Tag
      Box(
        modifier = Modifier
          .align(Alignment.BottomEnd)
          .padding(8.dp)
          .clip(RoundedCornerShape(6.dp))
          .background(Color.Black.copy(alpha = 0.8f))
          .border(0.5.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
          .padding(horizontal = 6.dp, vertical = 2.dp)
      ) {
        Text(
          text = duration,
          color = PlatinumWhite,
          fontSize = 11.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    Text(
      text = "NCERT CURATED VIDEO LECTURE",
      color = SilverMedium,
      fontSize = 10.sp,
      fontWeight = FontWeight.Bold,
      letterSpacing = 1.sp
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
      text = title,
      color = PlatinumWhite,
      fontSize = 14.sp,
      fontWeight = FontWeight.SemiBold,
      maxLines = 2,
      overflow = TextOverflow.Ellipsis
    )
  }
}
