package com.example.wake_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.*

@Parcelize
data class Alarm(
    var time: String = "00:00",
    var games: BooleanArray = booleanArrayOf(false, false, false, false, false),
    var weekdays: BooleanArray = booleanArrayOf(false, false, false, false, false, false, false),
    var name: String = " ",
    var vibrate: Boolean = true,
    var active: Boolean = true
) : Serializable, Parcelable {

    override fun toString(): String {
        return "Alarm(time='$time', description=$name, active=$active)"
    }
}