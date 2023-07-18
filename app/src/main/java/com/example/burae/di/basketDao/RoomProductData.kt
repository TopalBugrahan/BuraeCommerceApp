package com.example.burae.di.basketDao

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(entity = BasketData::class, parentColumns = ["bid"], childColumns = ["bid"], onDelete = CASCADE)])
data class RoomProductData(
    @PrimaryKey(autoGenerate = true)
    val ProductId: Int,
    val pid:Long,
    val bid: Int,
    val title: String,
    val imageUrl: String,
    val productCount:Int,
    val totalPrice:Long
)
