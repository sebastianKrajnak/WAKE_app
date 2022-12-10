package com.example.wake_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.wake_app.activity.ConstructWordGameActivity
import com.example.wake_app.activity.EquationMiniGameActivity
import com.example.wake_app.activity.SequenceGameActivity
import com.example.wake_app.model.Alarm


@RequiresApi(Build.VERSION_CODES.M)
fun Context.showNotificationWithFullScreenIntent(
    channelId: String = CHANNEL_ID,
    alarm: Alarm,
) {
    val builder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(android.R.drawable.arrow_up_float)
        .setContentTitle(alarm.name)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setFullScreenIntent(getFullScreenIntent(alarm, false), true)
        .addAction(R.drawable.sequence, "Dismiss",
            getFullScreenIntent(alarm, true))
        .setAutoCancel(true)


    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    with(notificationManager) {
        buildChannel(alarm)
        val notification = builder.build()
        notify(0, notification)
    }
}

private fun NotificationManager.buildChannel(alarm: Alarm) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "WAKE Alarm Channel"
        val descriptionText = "Full Screen Intent for alarm"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        channel.enableVibration(alarm.vibrate)
//        channel.setSound() // TODO get sound
        createNotificationChannel(channel)
    }
}

@RequiresApi(Build.VERSION_CODES.M)
private fun Context.getFullScreenIntent(alarm: Alarm, cancelNotification: Boolean): PendingIntent {
    val destination = decideGameActivity(alarm)
    val intent = Intent(this, destination)



    return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
}

private fun decideGameActivity(alarm: Alarm): Class<*> {
    // Todo get games from array and choose random by index


    if (alarm.games[0]) {
        return ConstructWordGameActivity::class.java
    } else if (alarm.games[1]) {
        return EquationMiniGameActivity::class.java
    } else {
        return SequenceGameActivity::class.java
    }
}

private const val CHANNEL_ID = "channelId"