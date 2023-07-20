package com.example.burae.di.basketDao

import androidx.room.*

@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertBasket(basketData: BasketData)

    @Insert
    fun insertProduct(product: RoomProductData)

    @Query("SELECT * FROM basket")
    fun getBasket(): List<BasketData>

    @Transaction
    @Query("SELECT pid,imageUrl,title,totalPrice, COUNT(pid) AS toplam FROM roomProduct WHERE bid = :bid GROUP BY imageUrl,title")
    fun getDistinctBasketWithProduct(bid: Int): List<BasketTotalData>

    @Transaction
    @Query("SELECT * FROM basket WHERE bid = :bid")
    fun getBasketWithProduct(bid: Int): BasketWithProduct

    @Query("DELETE FROM roomProduct WHERE bid = :bid")
    fun deleteBasketByUserId(bid: Int)

    @Query(
        "DELETE FROM roomProduct\n" +
                "WHERE ProductId in\n" +
                "(\n" +
                "  SELECT ProductId FROM roomProduct WHERE pid=:pid LIMIT 1\n" +
                ")"
    )
    fun updateProductCount( pid: Long)

}