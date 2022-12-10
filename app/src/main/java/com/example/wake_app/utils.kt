package com.example.wake_app

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.wake_app.activity.ClickSequenceGameActivity
import com.example.wake_app.activity.ConstructWordGameActivity
import com.example.wake_app.activity.EquationMiniGameActivity
import com.example.wake_app.activity.SequenceGameActivity
import com.example.wake_app.model.Alarm
import kotlin.random.Random


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
        .setOngoing(true)


    val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    with(notificationManager) {
        buildChannel(alarm)
        val notification = builder.build()
        notify(NOTIFICATION_CHANNEL_ID, notification)
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
            return EquationMiniGameActivity::class.java //change to the new game
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
private const val CHANNEL_ID = "channelId-wake"