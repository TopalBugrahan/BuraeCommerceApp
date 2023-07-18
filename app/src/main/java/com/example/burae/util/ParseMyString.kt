package com.example.burae.util

import android.util.Log
import com.example.burae.models.UserResponse

class ParseMyString {

    fun stringToUserResponse(data:String):UserResponse?{
        val dataRegex = """UserResponse\(id=(\d+), username=(.*?), email=(.*?), firstName=(.*?), lastName=(.*?), gender=(.*?), image=(.*?), token=(.*?)\)""".toRegex()
        val matchResult = dataRegex.find(data)
        if (matchResult != null) {
            val (id, username, email, firstName, lastName, gender, image, token) = matchResult.destructured
            val userResponse = UserResponse(id.toInt(), username, email, firstName, lastName, gender, image, token)
            //Log.d("userResponse",userResponse.toString())
            return userResponse
        }
        else{
            return null
        }
    }
}