package com.example.burae.di.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.burae.di.basketDao.BasketDao
import com.example.burae.di.basketDao.BasketData
import com.example.burae.di.basketDao.RoomProductData

@Database(entities = [SessionId::class,BasketData::class,RoomProductData::class], version = 1)
abstract class SessionDatabase : RoomDatabase() {

    abstract fun sessionDao(): SessionDao
    abstract fun basketDao():BasketDao

    companion object {
        private var instance: SessionDatabase? = null

        fun getBuraeDatabase(context: Context): SessionDatabase {

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    SessionDatabase::class.java,
                    "burae.db"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}