package com.example.wake_app.screens

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.wake_app.model.Alarm
import com.example.wake_app.showNotificationWithFullScreenIntent
import org.apache.commons.lang3.SerializationUtils


class AlarmNotification : BroadcastReceiver() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {

        val alarm : Alarm = SerializationUtils.deserialize(intent.getByteArrayExtra("alarm"))
        if (intent.getBooleanExtra(LOCK_SCREEN_KEY, true)) {
            context.showNotificationWithFullScreenIntent(true, alarm = alarm)
        } else {
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

private const val LOCK_SCREEN_KEY = "lockScreenKey"

