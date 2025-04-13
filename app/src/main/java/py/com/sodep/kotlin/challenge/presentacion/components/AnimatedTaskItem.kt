package py.com.sodep.kotlin.challenge.presentacion.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import py.com.sodep.kotlin.challenge.data.local.Task
import py.com.sodep.kotlin.challenge.presentacion.screens.TaskItem

@Composable
fun AnimatedTaskItem(task: Task, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        TaskItem(task = task, onClick = onClick)
    }
}