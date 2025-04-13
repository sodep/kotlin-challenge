package py.com.sodep.kotlin.challenge.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import py.com.sodep.kotlin.challenge.data.repository.TaskRepository
import kotlinx.coroutines.launch
import py.com.sodep.kotlin.challenge.data.local.Task
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor (private val repository: TaskRepository) : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> get() = _tasks

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getAllTasks()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task)
            loadTasks()
        }
    }

    fun getTaskById(taskId: Int): Task? {
        return _tasks.value.find { it.id == taskId }
    }

    fun filterTasksByPriority(priority: String) {
        viewModelScope.launch {
            val allTasks = repository.getAllTasks()
            _tasks.value = if (priority == "Todas") {
                allTasks
            } else {
                allTasks.filter { it.priority.equals(priority, ignoreCase = true) }
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repository.deleteTask(task)
            loadTasks()
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            loadTasks()
        }
    }
}