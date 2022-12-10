package com.example.wake_app.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.wake_app.model.Alarm
import com.example.wake_app.model.AlarmRepository
import com.example.wake_app.model.ExternalAlarmRepository
import com.example.wake_app.showNotificationWithFullScreenIntent
import org.apache.commons.lang3.SerializationUtils
import java.util.*


class AlarmNotification : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val alarm : Alarm = SerializationUtils.deserialize(intent.getByteArrayExtra("alarm"))
        val repo: AlarmRepository = ExternalAlarmRepository(context)

        if (repo.findAlarmByID(alarm.id!!) != null ) {
            val alarmFromRepo = repo.findAlarmByID(alarm.id!!)!!
            if (!alarmFromRepo.active) {
                return
            }

            if (isRepeatingAlarm(alarmFromRepo)) {
                context.showNotificationWithFullScreenIntent(alarm = alarmFromRepo)
                setAlarm(context, alarmFromRepo)
            }

            if (isSingleUserAlarm(alarmFromRepo)) {
                context.showNotificationWithFullScreenIntent(alarm = alarmFromRepo)
                alarmFromRepo.active = false
                repo.updateAlarm(alarmFromRepo)
            }
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

private fun isRepeatingAlarm(alarm: Alarm) : Boolean {
    return alarm.weekdays.contains(true)
}

private fun isSingleUserAlarm(alarm: Alarm): Boolean {
    return !alarm.weekdays.contains(true)
}

private const val LOCK_SCREEN_KEY = "lockScreenKey"

