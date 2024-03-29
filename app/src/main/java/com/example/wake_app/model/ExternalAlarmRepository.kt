package com.example.wake_app.model

import android.content.Context
import android.os.Environment
import java.io.*
import java.util.*

class ExternalAlarmRepository(var context: Context) : AlarmRepository {

    private val fileName = "alarms.txt"

    override fun addAlarm(alarm: Alarm) {

        var alarmList = getAlarmList()
        alarmList += alarm

        if (isExternalStorageWritable()) {
            FileOutputStream(noteFile(fileName)).use{ output ->
                val oos = ObjectOutputStream(output)
                oos.writeObject(alarmList)
                oos.close()
            }
        }
    }

    override fun getAlarmList(): List<Alarm> {
        try {
            return if (isExternalStorageReadable()) {
                FileInputStream(noteFile(fileName)).use { stream ->
                    val ois = ObjectInputStream(stream)
                    ois.readObject() as List<Alarm>
                }
            } else {
                emptyList<Alarm>()
            }
        }
        catch(E: java.lang.Exception){
            return emptyList<Alarm>()
        }
    }

    override fun deleteAlarm(alarm: Alarm) {
        var alarmList = getAlarmList()

        var newAlarmList =  emptyList<Alarm>()

        alarmList.forEach {
            if (!(it.id == alarm.id))
                newAlarmList += it
        }

        if (isExternalStorageWritable()) {
            FileOutputStream(noteFile(fileName)).use{ output ->
                val oos = ObjectOutputStream(output)
                oos.writeObject(newAlarmList)
                oos.close()
            }
        }
    }

    override fun findAlarmByID(id: UUID) : Alarm? {
        var alarmList = getAlarmList()
        var alarm: Alarm? = null
        alarmList.forEach {
            if (it.id == id)
                alarm = it
        }
        return alarm
    }

    override fun updateAlarm(old: Alarm, new: Alarm) {
        var alarmList = getAlarmList()
        var newAlarmList =  emptyList<Alarm>()

        alarmList.forEach {
            if (it.id == old.id)
                newAlarmList += new
            else
                newAlarmList += it
        }

        if (isExternalStorageWritable()) {
            FileOutputStream(noteFile(fileName)).use{ output ->
                val oos = ObjectOutputStream(output)
                oos.writeObject(newAlarmList)
                oos.close()
            }
        }
    }

    override fun updateAlarm(alarm: Alarm) {
        var alarmList = getAlarmList()
        var newAlarmList =  emptyList<Alarm>()

        alarmList.forEach {
            if (it.id == alarm.id)
                newAlarmList += alarm
            else
                newAlarmList += it
        }

        if (isExternalStorageWritable()) {
            FileOutputStream(noteFile(fileName)).use{ output ->
                val oos = ObjectOutputStream(output)
                oos.writeObject(newAlarmList)
                oos.close()
            }
        }
    }

    private fun AlarmDirectory(): File? = context.getExternalFilesDir(null)

    private fun noteFile(fileName: String): File = File(AlarmDirectory(), fileName)

    private  fun isExternalStorageWritable(): Boolean =
        Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED

    private fun isExternalStorageReadable(): Boolean =
        Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
}