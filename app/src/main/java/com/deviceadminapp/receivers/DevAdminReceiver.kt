package com.deviceadminapp.receivers

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.UserHandle
import android.util.Log


class DevAdminReceiver : DeviceAdminReceiver() {

    override fun onPasswordSucceeded(context: Context, intent: Intent, user: UserHandle) {
        Log.v("$$$$$$", "Password Succeeded")
    }

    override fun onPasswordFailed(context: Context, intent: Intent, user: UserHandle) {
        Log.v("$$$$$$", "Password Failed")
    }
}