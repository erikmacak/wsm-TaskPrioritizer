package com.erik.taskprioritizer

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.util.ValidationUtils
import kotlin.math.abs
import kotlin.test.*

class ValidationUtilsTest {

    private lateinit var sampleTasks: List<Task>

    @BeforeTest
    fun setup() {
        sampleTasks = listOf(
            Task(title = "Create PR"),
            Task(title = "Fix bug"),
            Task(title = "Write tests")
        )
    }

    // --- Title validation ---

    @Test
    fun testValidTitle() {
        assertFalse(ValidationUtils.isTaskTitleEmpty("Create PR"))
    }

    @Test
    fun testEmptyTitle() {
        assertTrue(ValidationUtils.isTaskTitleEmpty(""))
    }

    @Test
    fun testWhitespaceOnlyTitle() {
        assertTrue(ValidationUtils.isTaskTitleEmpty("   "))
    }

    // --- Title duplication checks ---

    @Test
    fun testTaskTitleIsNotRegistered() {
        assertFalse(ValidationUtils.isTaskTitleAlreadyRegistered("Add unit tests", sampleTasks))
    }

    @Test
    fun testTaskTitleIsAlreadyRegistered() {
        assertTrue(ValidationUtils.isTaskTitleAlreadyRegistered("Create PR", sampleTasks))
    }

    @Test
    fun testTaskTitleAlreadyRegisteredCaseInsensitive() {
        assertTrue(ValidationUtils.isTaskTitleAlreadyRegistered("create pr", sampleTasks))
    }

    // --- Weight validation ---

    @Test
    fun testWeightsSumIsNotEqualToOne() {
        val weights = mapOf(
            "Benefit" to 0.5f,
            "Complexity" to 0.5f,
            "Urgency" to 0.5f,
            "Risk" to 0.5f
        )
        assertFalse(ValidationUtils.isWeightSumEqualToOne(weights))
    }

    @Test
    fun testWeightsSumIsExactlyOne() {
        val weights = mapOf(
            "Benefit" to 0.2f,
            "Complexity" to 0.3f,
            "Urgency" to 0.2f,
            "Risk" to 0.3f
        )
        assertTrue(ValidationUtils.isWeightSumEqualToOne(weights))
    }

    @Test
    fun testWeightsSumIsNearlyOneDueToRounding() {
        val weights = mapOf(
            "Benefit" to 0.2f,
            "Complexity" to 0.3f,
            "Urgency" to 0.2f,
            "Risk" to 0.300001f
        )
        assertTrue(ValidationUtils.isWeightSumEqualToOne(weights))
    }
}