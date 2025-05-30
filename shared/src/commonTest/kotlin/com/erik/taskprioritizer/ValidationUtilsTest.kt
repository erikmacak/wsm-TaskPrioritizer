package com.erik.taskprioritizer

import com.erik.taskprioritizer.model.Task
import com.erik.taskprioritizer.util.ValidationUtils
import kotlin.test.*

class ValidationUtilsTest {

    private lateinit var sampleTasks: List<Task>

    @BeforeTest
    fun setup() {
        sampleTasks = listOf(
            Task(id = "1", title = "Create PR"),
            Task(id = "2", title = "Fix bug"),
            Task(id = "3", title = "Write tests")
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

    @Test
    fun testChangeTaskTitleWithDifferentTaskId() {
        assertTrue(ValidationUtils.isTaskTitleAlreadyRegisteredExceptCurrent("Create PR", sampleTasks, "4"))
    }

    @Test
    fun testChangeTaskTitleWithSameTaskId() {
        assertFalse(ValidationUtils.isTaskTitleAlreadyRegisteredExceptCurrent("Create PR", sampleTasks, "1"))
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

    // --- Tests for amount tasks in task repository ---

    @Test
    fun testOneTaskInTaskRepository() {
        val emptyList = emptyList<Task>()
        assertFalse(ValidationUtils.isAtLeastOneTaskInTaskRepository(emptyList))
    }

    @Test
    fun testNoTaskInTaskRepository() {
        val nonEmptyList = listOf(Task(title = "Sample Task"))
        assertTrue(ValidationUtils.isAtLeastOneTaskInTaskRepository(nonEmptyList))
    }
}