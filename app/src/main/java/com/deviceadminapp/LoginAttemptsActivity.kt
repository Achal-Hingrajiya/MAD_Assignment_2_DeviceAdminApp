package com.deviceadminapp

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.deviceadminapp.adapters.LoginAtteptAdapter
import com.deviceadminapp.models.LoginAttemptModel
import com.deviceadminapp.receivers.DevAdminReceiver

class LoginAttemptsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_attempts)

        supportActionBar?.hide()

        val componentName = ComponentName(this, DevAdminReceiver::class.java)
        val policyManager = getSystemService(DEVICE_POLICY_SERVICE) as DevicePolicyManager

        if (policyManager.isAdminActive(componentName)) {
            Log.v("$$$$$$", "Admin is already activated")


            LoginAttemptModel.addAttempt(LoginAttemptModel(true))
            LoginAttemptModel.addAttempt(LoginAttemptModel(true))
            LoginAttemptModel.addAttempt(LoginAttemptModel(false))
            LoginAttemptModel.addAttempt(LoginAttemptModel(true))
            LoginAttemptModel.addAttempt(LoginAttemptModel(false))
            LoginAttemptModel.addAttempt(LoginAttemptModel(true))
            LoginAttemptModel.addAttempt(LoginAttemptModel(false))
            LoginAttemptModel.addAttempt(LoginAttemptModel(true))
            LoginAttemptModel.addAttempt(LoginAttemptModel(false))



            val lvLoginAttempts = findViewById<ListView>(R.id.lv_login_attempts)
            val loginAttemptsAdapter = LoginAtteptAdapter(this, LoginAttemptModel.listOfAttempts)
            lvLoginAttempts.adapter = loginAttemptsAdapter

        } else {
            // get dev admin permission
            val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
            intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
            intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                getString(R.string.device_admin_explanation))
            startActivity(intent)
        }
    }
}