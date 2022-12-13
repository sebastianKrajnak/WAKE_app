package com.example.wake_app.activity

import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import com.example.wake_app.CHANNEL_ID
import com.example.wake_app.R

open class SoundAndVibration : ComponentActivity() {
    private var mp: MediaPlayer? = null

    // Vibrate
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = notificationManager.getNotificationChannel(CHANNEL_ID)

        val res = notification.description.split(",")
        if (res[0].toBoolean()) vibrate()
        playSound()

    }

    fun vibrate() {
        val DELAY = 0
        val VIBRATE = 1000
        val SLEEP = 1000
        val START = 0
        val vibratePattern = longArrayOf(DELAY.toLong(), VIBRATE.toLong(), SLEEP.toLong())

        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(vibratePattern, START));
        } else {
            // backward compatibility for Android API < 26
            // noinspection deprecation
            vibrator.vibrate(vibratePattern, START);
        }
    }

    fun vibrateStop() {
        val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            getSystemService(VIBRATOR_SERVICE) as Vibrator
        }
        vibrator.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        vibrateStop()
        stopSound()
    }

    fun playSound() {
        this.mp = MediaPlayer.create(this, R.raw.smash_mouth);
        mp?.start()
    }

    fun stopSound() {
        mp?.stop()
    }
}