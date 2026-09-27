package com.example

import com.example.data.curriculum.NcertCurriculumData
import com.example.model.NcertClass
import com.example.model.UserProfile
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
  @Test
  fun testGravityPointsLevelAndRankCalculations() {
    assertEquals("Stardust Scout", UserProfile.calculateRank(450))
    assertEquals(1, UserProfile.calculateLevel(450))

    assertEquals("Orbital Pioneer", UserProfile.calculateRank(1200))
    assertEquals(3, UserProfile.calculateLevel(1200))

    assertEquals("Stellar Scholar", UserProfile.calculateRank(3000))
    assertEquals(7, UserProfile.calculateLevel(3000))

    assertEquals("Cosmic Master", UserProfile.calculateRank(6000))
    assertEquals(13, UserProfile.calculateLevel(6000))

    assertEquals("Singularity Sovereign", UserProfile.calculateRank(12000))
    assertEquals(25, UserProfile.calculateLevel(12000))
  }

  @Test
  fun testCurriculumSubjectsAndChapters() {
    val subjectsC10 = NcertCurriculumData.getSubjectsForClass(NcertClass.CLASS_10)
    assertTrue(subjectsC10.isNotEmpty())
    assertTrue(subjectsC10.any { it.name == "Science" })
    assertTrue(subjectsC10.any { it.name == "Mathematics" })

    val scienceChapters = NcertCurriculumData.getChaptersForSubject("c10_sci")
    assertTrue(scienceChapters.size >= 5)

    val ch1 = scienceChapters.first()
    assertEquals("Chemical Reactions & Equations", ch1.title)
    assertTrue(ch1.subtopics.isNotEmpty())
    assertTrue(ch1.formulas.isNotEmpty())
    assertTrue(ch1.keyTakeaways.isNotEmpty())
    assertTrue(ch1.youtubeVideoId.isNotBlank())
  }

  @Test
  fun testQuizzesIntegrity() {
    val quizzes = NcertCurriculumData.getQuizzesForChapter("c10_sci_ch1")
    assertTrue(quizzes.isNotEmpty())
    val qSet = quizzes.first()
    assertTrue(qSet.questions.isNotEmpty())
    qSet.questions.forEach { q ->
      assertTrue(q.options.size >= 4)
      assertTrue(q.correctOptionIndex in q.options.indices)
      assertTrue(q.explanation.isNotBlank())
      assertTrue(q.conceptTag.isNotBlank())
    }
  }

  @Test
  fun testKnowledgeGraphIntegrity() {
    val nodes = NcertCurriculumData.getKnowledgeNodesForSubject("c10_sci")
    assertTrue(nodes.isNotEmpty())
    nodes.forEach { node ->
      assertTrue(node.masteryPercent in 0..100)
      assertTrue(node.title.isNotBlank())
    }
  }
}

