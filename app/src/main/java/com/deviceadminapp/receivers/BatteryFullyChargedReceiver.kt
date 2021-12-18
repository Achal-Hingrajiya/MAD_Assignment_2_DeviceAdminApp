package com.deviceadminapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.util.Log
import android.widget.Toast
import com.deviceadminapp.DashboardActivity

class BatteryFullyChargedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)

            if (status == BatteryManager.BATTERY_STATUS_FULL) {
                Log.v("$$$$$$", "Battery Full")
                Toast.makeText(context, "Battery Fully Charged", Toast.LENGTH_SHORT).show()
                context?.startService(DashboardActivity.mediaServiceIntent)
                context?.unregisterReceiver(this)
            }
        }
    }
}