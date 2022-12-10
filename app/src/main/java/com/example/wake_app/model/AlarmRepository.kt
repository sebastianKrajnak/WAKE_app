package com.example.wake_app.model

import java.util.*

interface AlarmRepository {
    fun addAlarm(alarm: Alarm)
    fun updateAlarm(old: Alarm, new: Alarm)
    fun getAlarmList(): List<Alarm>
    fun deleteAlarm(alarm: Alarm)
    fun findAlarmByID(id: UUID): Alarm?
}