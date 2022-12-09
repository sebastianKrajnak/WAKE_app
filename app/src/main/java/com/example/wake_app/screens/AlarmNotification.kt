package com.example.wake_app.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.wake_app.model.Alarm
import com.example.wake_app.showNotificationWithFullScreenIntent
import org.apache.commons.lang3.SerializationUtils
import java.util.*


class AlarmNotification : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        val alarm : Alarm = SerializationUtils.deserialize(intent.getByteArrayExtra("alarm"))
        if (checkIfAlarmIsValid(alarm)) {
            context.showNotificationWithFullScreenIntent(alarm = alarm)
        }
    }

    companion object {
        fun build(context: Context, isLockScreen: Boolean): Intent {
            return Intent(context, AlarmNotification::class.java).also {
                it.putExtra(LOCK_SCREEN_KEY, isLockScreen)
            }
        }
    }
}

fun checkIfAlarmIsValid(alarm: Alarm) : Boolean {
    if (alarm.weekdays.contains(true)) {
        if (alarm.weekdays[0] && Calendar.DAY_OF_WEEK == 1 ||
            alarm.weekdays[1] && Calendar.DAY_OF_WEEK == 2 ||
            alarm.weekdays[2] && Calendar.DAY_OF_WEEK == 3 ||
            alarm.weekdays[3] && Calendar.DAY_OF_WEEK == 4 ||
            alarm.weekdays[4] && Calendar.DAY_OF_WEEK == 5 ||
            alarm.weekdays[5] && Calendar.DAY_OF_WEEK == 6 ||
            alarm.weekdays[6] && Calendar.DAY_OF_WEEK == 7) {
            return true
        }
        return false
    }
    return true
}

private const val LOCK_SCREEN_KEY = "lockScreenKey"

