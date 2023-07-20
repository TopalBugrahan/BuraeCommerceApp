package com.example.burae.pref

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.example.burae.util.Ver
import javax.inject.Inject

class SessionManager @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getIsFirstRun()=sharedPreferences.getString(Ver.FIRST_RUN_KEY,null)

    fun setIsFirstRun(value:String){
        val editor = sharedPreferences.edit()
        editor.putString(Ver.FIRST_RUN_KEY,value)
        editor.apply()
    }


    fun deleteSession(){
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

}