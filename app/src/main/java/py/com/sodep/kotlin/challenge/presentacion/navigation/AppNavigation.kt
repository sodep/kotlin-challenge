package py.com.sodep.kotlin.challenge.presentacion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import py.com.sodep.kotlin.challenge.presentacion.screens.AddTaskScreen
import py.com.sodep.kotlin.challenge.presentacion.screens.EditTaskScreen
import py.com.sodep.kotlin.challenge.presentacion.screens.TaskDetailsScreen
import py.com.sodep.kotlin.challenge.presentacion.screens.TaskListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = "task_list") {
        composable("task_list") {
            TaskListScreen(
                onTaskClick = { taskId ->
                    navController.navigate("task_details/$taskId")
                },
                onAddTaskClick = {
                    navController.navigate("add_task")
                }
            )
        }
        composable("task_details/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            TaskDetailsScreen(
                taskId = taskId,
                navController = navController,
                onTaskUpdated = {
                    navController.navigate("edit_task/$taskId") },
                onTaskDeleted = {
                    navController.popBackStack()
                }
            )
        }
        composable("add_task") {
            AddTaskScreen(onTaskAdded = {
                navController.popBackStack()
            })
        }
        composable("edit_task/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            EditTaskScreen(
                taskId = taskId,
                onTaskUpdated = {
                    navController.popBackStack("task_list", inclusive = false)
                }
            )
        }
    }
}