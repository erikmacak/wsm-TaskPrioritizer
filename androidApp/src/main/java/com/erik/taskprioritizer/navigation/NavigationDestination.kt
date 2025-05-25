package com.erik.taskprioritizer.navigation

sealed class NavigationDestination(val route: String) {
    object TaskList : NavigationDestination("task_list")
    object PriorityTaskList: NavigationDestination("priority_task_list")
    object AddTask: NavigationDestination("add_task")
    object EditTask : NavigationDestination("edit_task")
    object RemoveTask: NavigationDestination("remove_task")
    object AdjustWeights: NavigationDestination("adjust_weights")
}