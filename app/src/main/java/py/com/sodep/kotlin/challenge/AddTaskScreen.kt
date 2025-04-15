package py.com.sodep.kotlin.challenge

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime

@Composable
fun AddTaskScreen(
    onSave: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(Priority.MEDIA) }

    // Luego podemos agregar campo para seleccionar fecha y hora
    val now = LocalDateTime.now()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Nueva Tarea", style = MaterialTheme.typography.titleLarge)

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
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
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
                onSave(
                    Task(
                        title = title,
                        description = null,
                        dateTime = now,
                        priority = priority
                    )
                )
            }) {
                Text("Guardar")
            }

            Spacer(modifier = Modifier.width(16.dp))

            OutlinedButton(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}
