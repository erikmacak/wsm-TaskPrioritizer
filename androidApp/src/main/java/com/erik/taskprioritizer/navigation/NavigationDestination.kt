package com.erik.taskprioritizer.navigation

sealed class NavigationDestination(val route: String) {
    object TaskList : NavigationDestination("task_list")
    object PriorityTaskList: NavigationDestination("priority_task_list")
    object AddTask: NavigationDestination("add_task")
    object AdjustWeights: NavigationDestination("adjust_weights")

    object EditTask : NavigationDestination("edit_task") {
        fun withId(taskId: String) = "edit_task/$taskId"
    }

    object RemoveTask : NavigationDestination("remove_task") {
        fun withId(taskId: String) = "remove_task/$taskId"
    }

}