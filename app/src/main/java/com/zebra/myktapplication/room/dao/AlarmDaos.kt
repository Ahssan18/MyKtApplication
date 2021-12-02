package com.zebra.myktapplication.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zebra.myktapplication.room.models.Alarm

@Dao
interface AlarmDaos {
    @Insert
    fun add(alarm: Alarm)

    @Delete
    fun delete(alarm: Alarm)

    @Query("SELECT * from alarm")
    fun getAllAlarm(): List<Alarm>


}