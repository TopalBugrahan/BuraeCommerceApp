package com.example.burae.di.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SessionDao {

    @Insert
    fun insert(sessionId: SessionId)


    @Query("SELECT*FROM session where uid=:uid")
    fun getSession(uid:Int):SessionId?
}