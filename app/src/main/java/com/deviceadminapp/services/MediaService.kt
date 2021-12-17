package com.deviceadminapp.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.deviceadminapp.R
import java.io.IOException


class MediaService : Service() {

    lateinit var media : MediaPlayer
    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        media = MediaPlayer.create(this, R.raw.alert)
        try {
            media.prepare()

        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        media.start()
        media.isLooping = true

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        media.release();

        super.onDestroy()
    }
}