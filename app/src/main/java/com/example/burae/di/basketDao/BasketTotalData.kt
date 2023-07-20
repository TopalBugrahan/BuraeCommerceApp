package com.example.burae.di.basketDao

import androidx.room.Embedded
import androidx.room.Relation

data class BasketTotalData(
    val pid:Long,
    val imageUrl:String,
    val title:String,
    val toplam:Int,
    val totalPrice:Long
)
