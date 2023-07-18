package com.example.burae.di.dao

import javax.inject.Inject

class SessionRepositroy @Inject constructor(private val sessionDao: SessionDao) {

    fun insertUser(sessionId: SessionId){
        sessionDao.insert(sessionId)
    }

    fun getUser(uid:Int) :SessionId?{
        return sessionDao.getSession(uid)
    }
}