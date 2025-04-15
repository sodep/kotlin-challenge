import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDateTime
import py.com.sodep.kotlin.challenge.Task
import py.com.sodep.kotlin.challenge.Priority


class TaskViewModel : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    private val _tasks = mutableStateListOf(
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
        )
    )

    val tasks: List<Task> get() = _tasks

    fun addTask(task: Task) {
        _tasks.add(task)
    }

    fun deleteTask(task: Task) {
        _tasks.remove(task)
    }

    fun updateTask(updatedTask: Task) {
        val index = _tasks.indexOfFirst { it.id == updatedTask.id }
        if (index != -1) {
            _tasks[index] = updatedTask
        }
    }
}
