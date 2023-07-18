package com.example.burae.di.basketDao

import androidx.room.*

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBasket(basketData: BasketData)

    @Insert
    fun insertProduct(product: RoomProductData)

    @Query("SELECT * FROM basket")
    fun getBasket():List<BasketData>

    @Transaction
    @Query("SELECT * FROM basket WHERE bid = :bid")
    fun getBasketWithProduct(bid: Int): BasketWithProduct

}