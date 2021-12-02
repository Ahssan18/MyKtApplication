package com.zebra.myktapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zebra.myktapplication.room.dao.AlarmDaos
import com.zebra.myktapplication.room.models.Alarm

@Database(entities = [Alarm::class],version = 1)
abstract class RoomDb : RoomDatabase() {

    abstract fun getAlarmDao():AlarmDaos
    companion object{
        var Instance:RoomDb?=null
        fun getInstance(context: Context):RoomDb
        {
            if(Instance==null)
            {
                Room.databaseBuilder(context,RoomDb::class.java,"Alarm")
                    .fallbackToDestructiveMigration().build()
            }
            return Instance!!
        }

    }
}