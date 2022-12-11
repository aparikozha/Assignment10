package com.example.assignment9

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private lateinit var btnExpandable : Button

    private val CHANNEL_ID = "Channel ID"
    private val NOTIFICATION_ID1 = 1
    private val NOTIFICATION_ID2 = 2
    private var id =3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnExpandable = findViewById(R.id.button)
        btnExpandable.setOnClickListener{
            showExpandNotification()}


    }

    private fun showExpandNotification(){
        var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.sample)

        var builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.notification)
            setContentTitle("Notification")
            setContentText("This is Expandable Notification")
            setLargeIcon(bitmap)
            setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            priority = NotificationCompat.PRIORITY_DEFAULT
        }


        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, "Test Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
       }

        with(NotificationManagerCompat.from(this)){
            notify(NOTIFICATION_ID1, builder.build())
        }
    }
}