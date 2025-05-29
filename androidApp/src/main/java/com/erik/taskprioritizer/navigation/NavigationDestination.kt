package com.erik.taskprioritizer.navigation

//Represents all navigation destinations in the app.
//Each destination has a unique route string used by the NavController.
sealed class NavigationDestination(val route: String) {
    object TaskList : NavigationDestination("task_list")
    object PriorityTaskList : NavigationDestination("priority_task_list")
    object AddTask : NavigationDestination("add_task")
    object AdjustWeights : NavigationDestination("adjust_weights")

    object EditTask : NavigationDestination("edit_task") {
        val routeWithArg = "$route/{taskId}"
        fun withId(taskId: String) = "$route/$taskId"
    }

    object RemoveTask : NavigationDestination("remove_task") {
        val routeWithArg = "$route/{taskId}"
        fun withId(taskId: String) = "$route/$taskId"
    }
}
