package com.example.burae.di.basketDao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class BasketData(

    @PrimaryKey()
    @ColumnInfo(name = "bid")
    var bid: Int = 0,

    @ColumnInfo(name = "basketSize")
    var basketSize: Int,

    )
