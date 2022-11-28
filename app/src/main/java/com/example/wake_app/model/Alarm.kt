package com.example.wake_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.*

@Parcelize
data class Alarm( val time: String = "00:00",
                  val description: String = " ",
                  val active: Boolean = true
) : Serializable, Parcelable {

    override fun toString(): String {
        return "Alarm(time='$time', description=$description, active=$active)"
    }
}