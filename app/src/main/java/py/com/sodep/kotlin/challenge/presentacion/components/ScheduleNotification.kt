package py.com.sodep.kotlin.challenge.presentacion.components

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import py.com.sodep.kotlin.challenge.ReminderReceiver

fun scheduleNotification(context: Context, taskId: Int, title: String, description: String, dateTime: Long) {
    val intent = Intent(context, ReminderReceiver::class.java).apply {
        putExtra("title", title)
        putExtra("description", description)
    }

    val pendingIntent = PendingIntent.getBroadcast(
        context,
        taskId,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, dateTime, pendingIntent)
}