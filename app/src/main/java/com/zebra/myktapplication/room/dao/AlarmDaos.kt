package com.zebra.myktapplication.room.dao

import androidx.room.*
import com.zebra.myktapplication.room.models.Alarm

@Dao
interface AlarmDaos {
    @Insert
    fun add(alarm: Alarm)

    @Delete
    fun deleteAlarm(alarm: Alarm)

    @Update
    fun update(alarm: Alarm)

    @Query("SELECT * from alarm")
    fun getAllAlarm(): List<Alarm>


}