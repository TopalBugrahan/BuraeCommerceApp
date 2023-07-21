package com.example.burae.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.burae.MainActivity
import com.example.burae.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

val channelId="notification_channel"
val channelName="com.example.burae"

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService:FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        //Log.d("aliali",message.toString())
        if(message.notification != null){
            Log.d("message",message.notification!!.title.toString())
            genereteNotification(message.notification!!.title!!,message.notification!!.body!!)
        }
    }

    fun getRemoteView(title:String,des:String):RemoteViews{
        val remoteViews=RemoteViews("com.example.burae",R.layout.notification)

        remoteViews.setTextViewText(R.id.notTitle,title)
        remoteViews.setTextViewText(R.id.notDes,des)
        remoteViews.setImageViewResource(R.id.notLogo,R.drawable.burea)

        return remoteViews
    }

    fun genereteNotification(title:String,des:String){
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pandingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder=NotificationCompat.Builder(applicationContext,
            channelId)
            .setSmallIcon(R.drawable.burea)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setOnlyAlertOnce(true)
            .setContentIntent(pandingIntent)

        builder=builder.setContent(getRemoteView(title ,des))

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            val notificationChannel= NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        notificationManager.notify(0,builder.build())
    }

}