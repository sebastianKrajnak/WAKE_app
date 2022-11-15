package com.example.wake_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GameButton(
    val name: String,
    @DrawableRes val iconResourceId: Int
)
