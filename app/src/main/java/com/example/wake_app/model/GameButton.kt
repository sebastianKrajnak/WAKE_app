package com.example.wake_app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class GameButton(
    var index: Int,
    @StringRes val name: Int,
    @DrawableRes val iconResourceId: Int
)
