package com.deviceadminapp.receivers

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent
import android.os.UserHandle
import android.util.Log
import com.deviceadminapp.LoginAttemptsActivity
import com.deviceadminapp.models.LoginAttemptModel


class DevAdminReceiver : DeviceAdminReceiver() {

    override fun onPasswordSucceeded(context: Context, intent: Intent, user: UserHandle) {
        Log.v("$$$$$$", "Password Succeeded")
        LoginAttemptModel.addAttempt(LoginAttemptModel(true))
        LoginAttemptsActivity.loginAttemptsAdapter.notifyDataSetChanged()
    }

    override fun onPasswordFailed(context: Context, intent: Intent, user: UserHandle) {
        Log.v("$$$$$$", "Password Failed")
        LoginAttemptModel.addAttempt(LoginAttemptModel(false))
        LoginAttemptsActivity.loginAttemptsAdapter.notifyDataSetChanged()
    }

}