package py.com.sodep.kotlin.challenge

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Tarea") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Título:", style = MaterialTheme.typography.labelLarge)
            Text(task.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Descripción:", style = MaterialTheme.typography.labelLarge)
            Text(task.description ?: "Sin descripción", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Fecha:", style = MaterialTheme.typography.labelLarge)
            Text(task.dateTime.toString(), style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Text("Prioridad: ${task.priority}", style = MaterialTheme.typography.labelLarge)

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Button(onClick = onEditClick) {
                    Text("Editar")
                }
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedButton(onClick = onDeleteClick) {
                    Text("Eliminar")
                }
            }
        }
    }
}
