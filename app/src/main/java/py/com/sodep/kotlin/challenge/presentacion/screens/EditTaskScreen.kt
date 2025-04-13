package py.com.sodep.kotlin.challenge.presentacion.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import py.com.sodep.kotlin.challenge.data.TaskViewModel

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun EditTaskScreen(
    taskId: Int?,
    onTaskUpdated: () -> Unit,
    viewModel: TaskViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadTasks()
    }

    val tasks = viewModel.tasks.collectAsState(initial = emptyList()).value
    val taskToEdit = tasks.find { it.id == taskId }

    val title = remember { mutableStateOf(taskToEdit?.title ?: "") }
    val description = remember { mutableStateOf(taskToEdit?.description ?: "") }
    val priority = remember { mutableStateOf(taskToEdit?.priority ?: "Alta") }
    val dateTime = remember { mutableStateOf(taskToEdit?.dateTime ?: "") }

    val isFormValid = title.value.isNotBlank() &&
            description.value.isNotBlank() &&
            priority.value.isNotBlank() &&
            dateTime.value.isNotBlank()

    val calendar = Calendar.getInstance()
    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }
    val priorities = listOf("Alta", "Media", "Baja")

    Column(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(title = "Editar recordatorio")

        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Editar Tarea", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = title.value,
                onValueChange = { title.value = it },
                label = { Text("Título") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = it }
            ) {
                OutlinedTextField(
                    value = priority.value,
                    onValueChange = {},
                    label = { Text("Prioridad") },
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) },
                    modifier = Modifier.menuAnchor().fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    priorities.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                priority.value = item
                                expanded.value = false
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    val datePicker = DatePickerDialog(
                        context,
                        { _, year, month, dayOfMonth ->
                            val timePicker = TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    calendar.set(year, month, dayOfMonth, hourOfDay, minute)
                                    dateTime.value = String.format(
                                        "%04d-%02d-%02d %02d:%02d",
                                        year, month + 1, dayOfMonth, hourOfDay, minute
                                    )
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true
                            )
                            timePicker.show()
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                    )
                    datePicker.show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (dateTime.value.isBlank()) "Seleccionar Fecha y Hora" else dateTime.value)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val updatedTask = taskToEdit?.copy(
                        title = title.value,
                        description = description.value,
                        priority = priority.value,
                        dateTime = dateTime.value
                    )
                    if (updatedTask != null) {
                        viewModel.updateTask(updatedTask)
                        onTaskUpdated()
                    }
                },
                enabled = isFormValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar Tarea")
            }
        }
    }


}