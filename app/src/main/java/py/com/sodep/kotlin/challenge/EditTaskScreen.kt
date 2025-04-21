package py.com.sodep.kotlin.challenge

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun EditTaskScreen(
    task: Task,
    onSave: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var priority by remember { mutableStateOf(task.priority) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Editar Tarea", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Prioridad:")
        Row {
            Priority.values().forEach { p ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    RadioButton(
                        selected = priority == p,
                        onClick = { priority = p }
                    )
                    Text(p.name)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Button(onClick = {
                onSave(task.copy(title = title, priority = priority))
            }) {
                Text("Guardar cambios")
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(onClick = { onDelete(task) }) {
                Text("Eliminar")
            }

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedButton(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}
