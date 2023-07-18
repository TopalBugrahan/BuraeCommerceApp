package com.example.burae.di.basketDao

import androidx.room.Embedded
import androidx.room.Relation

data class BasketWithProduct(
    @Embedded val basket: BasketData,
    @Relation(
        parentColumn = "bid",
        entityColumn = "bid"
    )
    val products: List<RoomProductData>
)
