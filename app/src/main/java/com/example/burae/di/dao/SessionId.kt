package com.example.burae.di.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "session")
data class SessionId(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sid")
    var sid: Int = 0,

    @ColumnInfo(name = "uid")
    var uid: Int,
)
