package com.church.app.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MusicPlayerService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    
    private var mediaPlayer: MediaPlayer? = null
    private val binder = LocalBinder()
    private var currentTrackUrl: String? = null
    
    inner class LocalBinder : Binder() {
        fun getService(): MusicPlayerService = this@MusicPlayerService
    }
    
    override fun onBind(intent: Intent): IBinder = binder
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "PLAY" -> playTrack(intent.getStringExtra("TRACK_URL"))
            "PAUSE" -> pauseTrack()
            "STOP" -> stopTrack()
        }
        return START_STICKY
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "music_player",
                "Music Player",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
    
    fun playTrack(url: String?) {
        url?.let {
            if (currentTrackUrl != it) {
                mediaPlayer?.release()
                mediaPlayer = MediaPlayer().apply {
                    setOnPreparedListener(this@MusicPlayerService)
                    setOnCompletionListener(this@MusicPlayerService)
                    setDataSource(it)
                    prepareAsync()
                    currentTrackUrl = it
                }
            } else {
                mediaPlayer?.start()
            }
            updateNotification("Воспроизведение...")
        }
    }
    
    fun pauseTrack() {
        mediaPlayer?.pause()
        updateNotification("Пауза")
    }
    
    fun stopTrack() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
        currentTrackUrl = null
        stopForeground(true)
        stopSelf()
    }
    
    override fun onPrepared(mp: MediaPlayer?) {
        mp?.start()
    }
    
    override fun onCompletion(mp: MediaPlayer?) {
        stopForeground(true)
    }
    
    private fun updateNotification(status: String) {
        val notification = NotificationCompat.Builder(this, "music_player")
            .setContentTitle("Церковное приложение")
            .setContentText(status)
            .setSmallIcon(android.R.drawable.ic_media_play)
            .setOngoing(true)
            .build()
        
        startForeground(1, notification)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
    }
}