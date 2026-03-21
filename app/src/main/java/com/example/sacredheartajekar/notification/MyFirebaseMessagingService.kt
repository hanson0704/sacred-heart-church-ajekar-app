package com.example.sacredheartajekar.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.sacredheartajekar.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        // 🔥 USE DATA FIRST (more reliable)
        val title = message.data["title"]
            ?: message.notification?.title
            ?: "Parish Update"

        val body = message.data["message"]
            ?: message.notification?.body
            ?: "New update available"

        showNotification(title, body)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        android.util.Log.d("FCM_TOKEN", token)

        com.google.firebase.messaging.FirebaseMessaging
            .getInstance()
            .subscribeToTopic("parish_updates")
    }

    private fun showNotification(title: String, body: String) {

        val channelId = "parish_updates"

        val manager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // 🔥 CHANNEL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Parish Updates",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Parish notifications"
                enableVibration(true)
            }
            manager.createNotificationChannel(channel)
        }

        // 🔥 INTENT (REQUIRED FOR POPUP)
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        val pendingIntent = android.app.PendingIntent.getActivity(
            this,
            0,
            intent,
            android.app.PendingIntent.FLAG_UPDATE_CURRENT or android.app.PendingIntent.FLAG_IMMUTABLE
        )

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setContentIntent(pendingIntent) // 🔥 THIS ENABLES POPUP
            .build()

        manager.notify(System.currentTimeMillis().toInt(), notification)
    }
}