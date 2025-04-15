package py.com.sodep.kotlin.challenge

import java.time.LocalDateTime
import py.com.sodep.kotlin.challenge.Task

import java.util.UUID

enum class Priority {
    ALTA, MEDIA, BAJA
}

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String? = null,
    val dateTime: LocalDateTime,
    val priority: Priority
)
