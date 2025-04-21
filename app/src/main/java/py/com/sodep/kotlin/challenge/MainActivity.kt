package py.com.sodep.kotlin.challenge

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import py.com.sodep.kotlin.challenge.ui.theme.KotlinchallengeTheme
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinchallengeTheme {
                val navController = rememberNavController()
                val taskList = remember { mutableStateListOf<Task>() }

                LaunchedEffect(Unit) {
                    taskList.addAll(
                        listOf(
                            Task(
                                title = "Tarea 1",
                                description = "Descripción 1",
                                dateTime = LocalDateTime.now(),
                                priority = Priority.ALTA
                            ),
                            Task(
                                title = "Tarea 2",
                                description = "Descripción 2",
                                dateTime = LocalDateTime.now().plusHours(2),
                                priority = Priority.MEDIA
                            ),
                            Task(
                                title = "Tarea 3",
                                description = "Descripción 3",
                                dateTime = LocalDateTime.now().plusDays(1),
                                priority = Priority.BAJA
                            )
                        )
                    )
                }

                NavHost(
                    navController = navController,
                    startDestination = "welcome"
                ) {
                    composable("welcome") {
                        WelcomeScreen(onEnterClick = {
                            navController.navigate("taskList")
                        })
                    }

                    composable("taskList") {
                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            floatingActionButton = {
                                FloatingActionButton(onClick = {
                                    navController.navigate("addTask")
                                }) {
                                    Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
                                }
                            }
                        ) { innerPadding ->
                            TaskListScreen(
                                tasks = taskList,
                                onTaskClick = { task ->
                                    navController.navigate("editTask/${task.id}")
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }

                    composable("addTask") {
                        AddTaskScreen(
                            onSave = { newTask ->
                                taskList.add(newTask)
                                navController.popBackStack()
                            },
                            onCancel = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable("editTask/{taskId}") { backStackEntry ->
                        val taskId = backStackEntry.arguments?.getString("taskId")
                        val task = taskList.find { it.id == taskId }

                        if (task != null) {
                            EditTaskScreen(
                                task = task,
                                onSave = { updatedTask ->
                                    val index = taskList.indexOfFirst { it.id == task.id }
                                    if (index != -1) {
                                        taskList[index] = updatedTask
                                    }
                                    navController.popBackStack()
                                },
                                onDelete = { taskToDelete ->
                                    taskList.remove(taskToDelete)
                                    navController.popBackStack()
                                },
                                onCancel = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                } //
            } //
        } //
    } //
} //
