package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
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
import com.example.model.QuizQuestion
import com.example.model.QuizSet
import com.example.ui.components.GlassButton
import com.example.ui.components.GlassCard
import com.example.ui.theme.*

@Composable
fun QuizScreen(
  quizSet: QuizSet,
  onQuizFinished: (score: Int, total: Int, rewardGp: Int) -> Unit,
  onBackClick: () -> Unit,
  onAskAiDoubt: (QuizQuestion) -> Unit,
  modifier: Modifier = Modifier
) {
  var currentQuestionIndex by remember { mutableStateOf(0) }
  var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
  var isSubmitted by remember { mutableStateOf(false) }
  var score by remember { mutableStateOf(0) }
  var isQuizComplete by remember { mutableStateOf(false) }

  val questions = quizSet.questions
  val currentQuestion = questions.getOrNull(currentQuestionIndex)

  if (isQuizComplete || currentQuestion == null) {
    // Score & Reward Result Screen
    Column(
      modifier = modifier
        .fillMaxSize()
        .background(ObsidianVoid)
        .padding(24.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Box(
        modifier = Modifier
          .size(80.dp)
          .clip(CircleShape)
          .background(Color.White.copy(alpha = 0.1f))
          .border(2.dp, PlatinumWhite, CircleShape),
        contentAlignment = Alignment.Center
      ) {
        Text("🏆", fontSize = 36.sp)
      }

      Spacer(modifier = Modifier.height(20.dp))

      Text(
        text = "ASSESSMENT COMPLETED",
        color = SilverMedium,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 2.sp
      )

      Text(
        text = "Score: $score / ${questions.size}",
        color = PlatinumWhite,
        fontSize = 28.sp,
        fontWeight = FontWeight.Black
      )

      val percent = if (questions.isNotEmpty()) (score * 100) / questions.size else 0
      val earnedGp = (quizSet.rewardGp * (percent / 100f)).toInt().coerceAtLeast(30)

      Spacer(modifier = Modifier.height(10.dp))

      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(GravityAmber.copy(alpha = 0.15f))
          .border(1.dp, GravityAmber.copy(alpha = 0.3f), RoundedCornerShape(20.dp))
          .padding(horizontal = 14.dp, vertical = 6.dp)
      ) {
        Text(
          text = "+$earnedGp GRAVITY POINTS EARNED",
          color = GravityAmber,
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )
      }

      Spacer(modifier = Modifier.height(32.dp))

      GlassButton(
        text = "RETURN TO CHAPTER",
        onClick = { onQuizFinished(score, questions.size, earnedGp) },
        modifier = Modifier.fillMaxWidth()
      )
    }
    return
  }

  // Active Quiz Question View
  Column(
    modifier = modifier
      .fillMaxSize()
      .background(ObsidianVoid)
      .verticalScroll(rememberScrollState())
      .padding(16.dp)
  ) {
    // Header & Question Progress
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      IconButton(onClick = onBackClick) {
        Icon(Icons.Default.Close, contentDescription = "Close Quiz", tint = PlatinumWhite)
      }

      Text(
        text = "QUESTION ${currentQuestionIndex + 1} OF ${questions.size}",
        color = SilverMedium,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold
      )

      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(12.dp))
          .background(Color.White.copy(alpha = 0.08f))
          .padding(horizontal = 8.dp, vertical = 4.dp)
      ) {
        Text(
          text = quizSet.difficulty.name,
          color = PlatinumWhite,
          fontSize = 10.sp,
          fontWeight = FontWeight.Bold
        )
      }
    }

    Spacer(modifier = Modifier.height(8.dp))

    // Linear Progress Bar
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .height(4.dp)
        .clip(RoundedCornerShape(2.dp))
        .background(Color.White.copy(alpha = 0.1f))
    ) {
      Box(
        modifier = Modifier
          .fillMaxHeight()
          .fillMaxWidth((currentQuestionIndex + 1).toFloat() / questions.size)
          .clip(RoundedCornerShape(2.dp))
          .background(PlatinumWhite)
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Question Card
    GlassCard(modifier = Modifier.fillMaxWidth()) {
      Text(
        text = currentQuestion.conceptTag.uppercase(),
        color = SilverMedium,
        fontSize = 10.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp
      )
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = currentQuestion.questionText,
        color = PlatinumWhite,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 22.sp
      )
    }

    Spacer(modifier = Modifier.height(16.dp))

    // Options List
    currentQuestion.options.forEachIndexed { index, optionText ->
      val isSelected = selectedOptionIndex == index
      val isCorrect = isSubmitted && index == currentQuestion.correctOptionIndex
      val isWrong = isSubmitted && isSelected && index != currentQuestion.correctOptionIndex

      val borderColor = when {
        isCorrect -> MasteryEmerald
        isWrong -> UrgentRose
        isSelected -> PlatinumWhite
        else -> Color.White.copy(alpha = 0.15f)
      }

      val bgColor = when {
        isCorrect -> MasteryEmerald.copy(alpha = 0.15f)
        isWrong -> UrgentRose.copy(alpha = 0.15f)
        isSelected -> Color.White.copy(alpha = 0.18f)
        else -> Color.White.copy(alpha = 0.04f)
      }

      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(12.dp))
          .background(bgColor)
          .border(1.dp, borderColor, RoundedCornerShape(12.dp))
          .clickable(enabled = !isSubmitted) { selectedOptionIndex = index }
          .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        val letter = ('A' + index).toString()
        Box(
          modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(if (isSelected || isCorrect) PlatinumWhite else Color.White.copy(alpha = 0.1f)),
          contentAlignment = Alignment.Center
        ) {
          Text(
            text = letter,
            color = if (isSelected || isCorrect) ObsidianVoid else PlatinumWhite,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
          )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Text(
          text = optionText,
          color = PlatinumWhite,
          fontSize = 14.sp,
          modifier = Modifier.weight(1f)
        )

        if (isCorrect) {
          Icon(Icons.Default.Check, contentDescription = "Correct", tint = MasteryEmerald)
        } else if (isWrong) {
          Icon(Icons.Default.Close, contentDescription = "Wrong", tint = UrgentRose)
        }
      }
      Spacer(modifier = Modifier.height(8.dp))
    }

    // Explanation & Hint Card
    AnimatedVisibility(visible = isSubmitted) {
      Column {
        Spacer(modifier = Modifier.height(10.dp))
        GlassCard(modifier = Modifier.fillMaxWidth()) {
          Text(
            text = "NCERT EXPLANATION",
            color = SilverMedium,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = currentQuestion.explanation,
            color = SilverBright,
            fontSize = 13.sp,
            lineHeight = 18.sp
          )

          Spacer(modifier = Modifier.height(10.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
          ) {
            Text(
              text = "Ask AI to expand doubt ->",
              color = PlatinumWhite,
              fontSize = 12.sp,
              fontWeight = FontWeight.SemiBold,
              modifier = Modifier.clickable { onAskAiDoubt(currentQuestion) }
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Action Button
    if (!isSubmitted) {
      GlassButton(
        text = "SUBMIT ANSWER",
        onClick = {
          if (selectedOptionIndex != null) {
            isSubmitted = true
            if (selectedOptionIndex == currentQuestion.correctOptionIndex) {
              score++
            }
          }
        },
        enabled = selectedOptionIndex != null,
        modifier = Modifier.fillMaxWidth()
      )
    } else {
      GlassButton(
        text = if (currentQuestionIndex < questions.size - 1) "NEXT QUESTION" else "FINISH QUIZ",
        onClick = {
          if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            selectedOptionIndex = null
            isSubmitted = false
          } else {
            isQuizComplete = true
          }
        },
        modifier = Modifier.fillMaxWidth()
      )
    }

    Spacer(modifier = Modifier.height(40.dp))
  }
}
