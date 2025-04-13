package py.com.sodep.kotlin.challenge.presentacion.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import py.com.sodep.kotlin.challenge.data.TaskViewModel
import py.com.sodep.kotlin.challenge.presentacion.components.LoadingIndicator
import py.com.sodep.kotlin.challenge.presentacion.components.StyledButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen(
    taskId: Int?,
    navController: NavController,
    viewModel: TaskViewModel = hiltViewModel(),
    onTaskUpdated: () -> Unit,
    onTaskDeleted: () -> Unit,
) {
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    val task = remember(taskId, tasks) { tasks.find { it.id == taskId } }

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(title = "Detalles de la Tarea", onBackClick = { onTaskDeleted() })

        if (task != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Título:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Descripción:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = task.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Prioridad:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = task.priority,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = "Fecha y Hora:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = task.dateTime,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                Column {
                    StyledButton(
                        text = "Eliminar Tarea",
                        onClick = {
                            viewModel.deleteTask(task)
                            onTaskDeleted()
                        },
                        icon = Icons.Default.Delete
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    StyledButton(
                        text = "Editar Tarea",
                        onClick = {
                            taskId?.let { id ->
                                navController.navigate("edit_task/$id")
                            }
                        },
                        icon = Icons.Default.Edit
                    )
                }
            }
        } else {
            LoadingIndicator()
        }
    }
}