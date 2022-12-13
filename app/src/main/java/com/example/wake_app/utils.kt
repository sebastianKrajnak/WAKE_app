package com.example.wake_app

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.wake_app.activity.*
import com.example.wake_app.model.Alarm


@RequiresApi(Build.VERSION_CODES.M)
fun Context.showNotificationWithFullScreenIntent(
    channelId: String = CHANNEL_ID,
    alarm: Alarm,
) {
    val alarmSound : Uri = Uri.parse("android.resource://"+ this.getPackageName()+"/"+R.raw.smash_mouth)
    val pattern = longArrayOf(500, 500, 500, 500, 500, 500, 500, 500, 500)
    val builder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(android.R.drawable.arrow_up_float)
        .setContentTitle(alarm.name)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setFullScreenIntent(getFullScreenIntent(alarm, false), true)
        .addAction(R.drawable.sequence, "Dismiss",
            getFullScreenIntent(alarm, true))
        .setAutoCancel(true)
        .setOngoing(true)
        .setSound(alarmSound)

    if (alarm.vibrate) builder.setVibrate(pattern)

    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    with(notificationManager) {
        buildChannel(alarm, alarmSound)
        val notification = builder.build()
        notification.flags = Notification.FLAG_INSISTENT
        notify(NOTIFICATION_CHANNEL_ID, notification)
    }
}

private fun NotificationManager.buildChannel(alarm: Alarm, alarmSound: Uri) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "WAKE Alarm Channel"
        val descriptionText = ""  + alarm.vibrate + "," + false
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        channel.enableVibration(alarm.vibrate)
        val attributes = AudioAttributes.Builder()
            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
            .setUsage(AudioAttributes.USAGE_ALARM)
            .build()
        channel.setSound(alarmSound, attributes)
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
    val gameIndex = ArrayList<Int>()
    alarm.games.forEachIndexed{ index, element ->
        if (element)
            gameIndex += index
    }


    val chosenGame = gameIndex[Math.floor(Math.random() * gameIndex.size).toInt()]

    when (chosenGame) {
        0 -> {
            return ClickSequenceGameActivity::class.java
        }
        1 -> {
            return EquationMiniGameActivity::class.java
        }
        2 -> {
            return MemorizeSequenceActivity::class.java //change to the new game
        }
        3 -> {
            return SequenceGameActivity::class.java
        }
        else -> {
            return ConstructWordGameActivity::class.java
        }
    }
}

const val BROADCAST_REQUEST_CODE = 1597
const val NOTIFICATION_CHANNEL_ID = 1597
const val CHANNEL_ID = "channelId-wake"