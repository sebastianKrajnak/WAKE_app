package com.example.wake_app.model

import java.io.*


data class Alarm( val time: String = "00:00",
                  val description: String = " ",
                  val active: Boolean = true
) : Serializable {

    override fun toString(): String {
        return "Alarm(time='$time', description=$description, active=$active)"
    }
}