package com.zebra.myktapplication.room.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.zebra.myktapplication.room.RoomDb

@Entity(tableName = "Alarm")
data class Alarm @Ignore constructor(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo(name = "time")
        var time: String,
        @ColumnInfo(name = "timeUnit")
        var timeUnit: String,
        @ColumnInfo(name = "schedule")
        var schedule: String,
        @ColumnInfo(name = "vibrate")
        var vibrate: Boolean,
        @ColumnInfo(name = "alarmstatus")
        var alarmstatus: Boolean
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readByte() != 0.toByte(),
            parcel.readByte() != 0.toByte()) {
    }

    constructor(time: String, timeUnit: String, schedule: String, vibrate: Boolean, alarmstatus: Boolean) : this(0, time, timeUnit, schedule, vibrate, alarmstatus)


    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(this.id)
        dest?.writeString(this.time)
        dest?.writeString(this.timeUnit)
        dest?.writeString(this.schedule)
        dest?.writeInt(if (this.vibrate) 1 else 0)
        dest?.writeInt(if (this.alarmstatus) 1 else 0)
    }

    companion object CREATOR : Parcelable.Creator<Alarm> {
        override fun createFromParcel(parcel: Parcel): Alarm {
            return Alarm(parcel)
        }

        override fun newArray(size: Int): Array<Alarm?> {
            return arrayOfNulls(size)
        }
    }
}
