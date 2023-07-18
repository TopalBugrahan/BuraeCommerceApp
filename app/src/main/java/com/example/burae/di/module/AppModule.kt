package com.example.burae.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.burae.di.basketDao.BasketDao
import com.example.burae.di.dao.SessionDao
import com.example.burae.di.dao.SessionDatabase
import com.example.burae.di.retrofit.DummyRetrofitServiceInstance
import com.example.burae.pref.SessionManager
import com.example.burae.util.Ver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val BASE_URL="https://dummyjson.com/"

    @Singleton
    @Provides
    fun getSharedPreferences(@ApplicationContext context: Context):SharedPreferences{
        return context.getSharedPreferences(Ver.PREF_NAME,Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun getRoomDb(context:Application):SessionDatabase{
        return SessionDatabase.getBuraeDatabase(context)
    }

    @Singleton
    @Provides
    fun getDao(sessionDatabase: SessionDatabase):SessionDao{
        return sessionDatabase.sessionDao()
    }

    @Singleton
    @Provides
    fun getBasketDao(sessionDatabase: SessionDatabase):BasketDao{
        return  sessionDatabase.basketDao()
    }

   /* @Singleton
    @Provides
    fun getSessionManager(sharedPreferences: SharedPreferences):SessionManager{
        return SessionManager(sharedPreferences)
    }*/

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getDummyRetrofitServiceInstance(retrofit:Retrofit):DummyRetrofitServiceInstance{
        return retrofit.create(DummyRetrofitServiceInstance::class.java)
    }
}