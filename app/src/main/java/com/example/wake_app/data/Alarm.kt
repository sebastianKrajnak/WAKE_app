package com.example.wake_app.data

import androidx.annotation.StringRes
import com.example.wake_app.R

data class Alarm(
    @StringRes val time: Int,
    @StringRes val description: Int,
    val active: Boolean = true
)