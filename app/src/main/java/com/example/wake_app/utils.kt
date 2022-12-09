package com.example.wake_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.wake_app.model.Alarm


@RequiresApi(Build.VERSION_CODES.M)
fun Context.showNotificationWithFullScreenIntent(
    isLockScreen: Boolean = false,
    channelId: String = CHANNEL_ID,
    alarm: Alarm,
) {
    val alarmExtras = Bundle()
    alarmExtras.putString("alarm", alarm.toString())

    val builder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(android.R.drawable.arrow_up_float)
        .setContentTitle(alarm.name)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setFullScreenIntent(getFullScreenIntent(isLockScreen,alarm), true)
        .setExtras(alarmExtras)

    builder.addExtras(alarmExtras)
    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    with(notificationManager) {
        buildChannel()
        val notification = builder.build()

        notify(1, notification)
    }
}

private fun NotificationManager.buildChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "WAKE Alarm Channel"
        val descriptionText = "Full Screen Intent for alarm"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        createNotificationChannel(channel)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
private fun Context.getFullScreenIntent(isLockScreen: Boolean, alarm: Alarm): PendingIntent {
    val destination = if (isLockScreen)
        AlarmActivity::class.java
    else
        AlarmActivity::class.java
    val intent = Intent(this, destination)
    intent.putExtra("alarm", alarm.toString())
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)

    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
}

private const val CHANNEL_ID = "channelId"