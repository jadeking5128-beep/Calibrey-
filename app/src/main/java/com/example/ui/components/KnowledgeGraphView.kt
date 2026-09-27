package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.KnowledgeNode
import com.example.model.NodeStatus
import com.example.ui.theme.*

@Composable
fun KnowledgeGraphView(
  nodes: List<KnowledgeNode>,
  onNodeClick: (KnowledgeNode) -> Unit,
  modifier: Modifier = Modifier
) {
  var selectedNode by remember { mutableStateOf(nodes.firstOrNull()) }

  Column(modifier = modifier.fillMaxWidth()) {
    // Interactive Graph Canvas Area
    GlassCard(
      modifier = Modifier
        .fillMaxWidth()
        .height(280.dp),
      backgroundAlpha = 0.05f
    ) {
      Box(modifier = Modifier.fillMaxSize()) {
        // Background Grid and Lines
        Canvas(modifier = Modifier.fillMaxSize()) {
          val canvasWidth = size.width
          val canvasHeight = size.height

          // Draw connections between nodes
          nodes.forEachIndexed { index, node ->
            val col = index % 3
            val row = index / 3
            val startX = (canvasWidth / 4) * (col + 1)
            val startY = (canvasHeight / 5) * (row + 1)

            node.connectedNodeIds.forEach { targetId ->
              val targetIndex = nodes.indexOfFirst { it.id == targetId }
              if (targetIndex >= 0) {
                val targetCol = targetIndex % 3
                val targetRow = targetIndex / 3
                val endX = (canvasWidth / 4) * (targetCol + 1)
                val endY = (canvasHeight / 5) * (targetRow + 1)

                drawLine(
                  color = Color.White.copy(alpha = 0.2f),
                  start = Offset(startX, startY),
                  end = Offset(endX, endY),
                  strokeWidth = 2.dp.toPx(),
                  cap = StrokeCap.Round
                )
              }
            }
          }
        }

        // Nodes Overlay
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
          val maxWidthPx = maxWidth
          val maxHeightPx = maxHeight

          nodes.take(9).forEachIndexed { index, node ->
            val col = index % 3
            val row = index / 3
            val xOffset = (maxWidthPx / 4) * (col + 1) - 24.dp
            val yOffset = (maxHeightPx / 4.5f) * (row + 1) - 24.dp

            val isSelected = selectedNode?.id == node.id
            val statusColor = when (node.status) {
              NodeStatus.MASTERED -> MasteryEmerald
              NodeStatus.IN_PROGRESS -> PlatinumWhite
              NodeStatus.REVISION_NEEDED -> GravityAmber
              NodeStatus.WEAK_AREA -> UrgentRose
              NodeStatus.LOCKED -> SilverMuted
            }

            Box(
              modifier = Modifier
                .offset(x = xOffset, y = yOffset)
                .size(48.dp)
                .clip(CircleShape)
                .background(ObsidianDark)
                .border(
                  width = if (isSelected) 2.5.dp else 1.5.dp,
                  color = if (isSelected) PlatinumWhite else statusColor.copy(alpha = 0.8f),
                  shape = CircleShape
                )
                .clickable {
                  selectedNode = node
                  onNodeClick(node)
                },
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "${node.masteryPercent}%",
                color = PlatinumWhite,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(10.dp))

    // Selected Node Details Glass Panel
    val active = selectedNode ?: nodes.firstOrNull()
    if (active != null) {
      GlassCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = { onNodeClick(active) }
      ) {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Column(modifier = Modifier.weight(1f)) {
            Text(
              text = active.title,
              color = PlatinumWhite,
              fontSize = 15.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
              text = "Formula: ${active.keyFormula.ifBlank { "Standard NCERT Principle" }}",
              color = SilverMedium,
              fontSize = 12.sp
            )
          }

          val statusBadge = when (active.status) {
            NodeStatus.MASTERED -> "MASTERED" to MasteryEmerald
            NodeStatus.IN_PROGRESS -> "IN PROGRESS" to PlatinumWhite
            NodeStatus.REVISION_NEEDED -> "REVISION" to GravityAmber
            NodeStatus.WEAK_AREA -> "WEAK AREA" to UrgentRose
            NodeStatus.LOCKED -> "LOCKED" to SilverMuted
          }

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(8.dp))
              .background(statusBadge.second.copy(alpha = 0.15f))
              .border(0.5.dp, statusBadge.second.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
              .padding(horizontal = 8.dp, vertical = 4.dp)
          ) {
            Text(
              text = statusBadge.first,
              color = statusBadge.second,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold
            )
          }
        }
      }
    }
  }
}
