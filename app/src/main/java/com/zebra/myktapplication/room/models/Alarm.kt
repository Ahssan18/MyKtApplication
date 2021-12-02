package com.zebra.myktapplication.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Alarm")
data class Alarm(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var time: String,
    var timeUnit: String,
    var schedule: String
)
