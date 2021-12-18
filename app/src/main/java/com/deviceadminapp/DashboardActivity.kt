package com.deviceadminapp

import android.content.*
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.deviceadminapp.receivers.BatteryFullyChargedReceiver
import com.deviceadminapp.receivers.PowerConnectionReceiver
import com.deviceadminapp.services.MediaService
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    companion object {
        lateinit var mediaServiceIntent: Intent
        lateinit var sharedPreferences: SharedPreferences
        const val IS_POWER_CONN_ALARM_ACTIVE = "isPowerConnectionAlarmActive"
        const val IS_FULL_CHARGE_ALARM_ACTIVE = "isFullChargeAlarmActive"
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()

        mediaServiceIntent = Intent(this, MediaService::class.java)
        sharedPreferences = getSharedPreferences("Dashboard", Context.MODE_PRIVATE)

        val bNav = findViewById<BottomNavigationView>(R.id.bottom_nav_dashboard)
        bNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_login_attempt -> {
                    Intent(this, LoginAttemptsActivity::class.java).apply {
                        startActivity(this)
                    }
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener true
                }
            }
        }

        bNav.selectedItemId = R.id.menu_dashboard

        val btnPowerConnection = findViewById<Button>(R.id.btn_activate_power_connection)
        btnPowerConnection.setOnClickListener {
            lateinit var receiver: PowerConnectionReceiver

            val isActive = sharedPreferences.getBoolean(IS_POWER_CONN_ALARM_ACTIVE, false)

            if (isActive) {
                btnPowerConnection.backgroundTintList =
                    this.resources.getColorStateList(R.color.green)
                btnPowerConnection.text = getString(R.string.activate_power_connection_alarm)
                stopService(mediaServiceIntent)


                val editor = sharedPreferences.edit()
                editor.putBoolean(IS_POWER_CONN_ALARM_ACTIVE, false)
                editor.apply()
            } else {
                if (isConnected(this)) {

                    receiver = PowerConnectionReceiver()

                    val filter = IntentFilter()
                    filter.addAction(Intent.ACTION_POWER_CONNECTED)
                    filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
                    registerReceiver(receiver, filter)

                    btnPowerConnection.backgroundTintList =
                        this.resources.getColorStateList(R.color.red)
                    btnPowerConnection.text = getString(R.string.deactivate_power_connection_alarm)

                    val editor = sharedPreferences.edit()
                    editor.putBoolean(IS_POWER_CONN_ALARM_ACTIVE, true)
                    editor.apply()

                    Toast.makeText(this, "Alarm Activated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Please Connect Your Charger First", Toast.LENGTH_LONG)
                        .show();

                }

            }
        }

        val btnFullCharge = findViewById<Button>(R.id.btn_activate_full_charge)
        btnFullCharge.setOnClickListener {
            val fullChargeBR = BatteryFullyChargedReceiver()

            val isFullChargeActive = sharedPreferences.getBoolean(IS_FULL_CHARGE_ALARM_ACTIVE, false)
            if(!isFullChargeActive){
                btnFullCharge.backgroundTintList =
                    this.resources.getColorStateList(R.color.red)
                btnFullCharge.text = getString(R.string.deactivate_full_charge_alarm)

                registerReceiver(fullChargeBR, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

                val editor = sharedPreferences.edit()
                editor.putBoolean(IS_FULL_CHARGE_ALARM_ACTIVE, true)
                editor.apply()
            }
            else{
                stopService(mediaServiceIntent)
                btnFullCharge.backgroundTintList =
                    this.resources.getColorStateList(R.color.green)
                btnFullCharge.text = getString(R.string.activate_full_charge_alarm)

                val editor = sharedPreferences.edit()
                editor.putBoolean(IS_FULL_CHARGE_ALARM_ACTIVE, false)
                editor.apply()
            }
        }
    }

    private fun isConnected(context: Context): Boolean {
        val intent: Intent =
            context.registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))!!
        val plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        return plugged == BatteryManager.BATTERY_PLUGGED_AC || plugged == BatteryManager.BATTERY_PLUGGED_USB
    }
}