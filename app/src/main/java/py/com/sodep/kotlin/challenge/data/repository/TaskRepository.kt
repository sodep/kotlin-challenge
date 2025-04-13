package py.com.sodep.kotlin.challenge.data.repository

import py.com.sodep.kotlin.challenge.data.local.TaskDao
import py.com.sodep.kotlin.challenge.data.local.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    suspend fun getAllTasks() = taskDao.getAllTasks()
    suspend fun insertTask(task: Task) = taskDao.insertTask(task)
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
}