package com.deviceadminapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.deviceadminapp.DashboardActivity

class PowerConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent != null){
            if (intent.action.equals(Intent.ACTION_POWER_CONNECTED)) {
                Log.v("$$$$$$", "Power Connected")
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            } else {
                intent.action.equals(Intent.ACTION_POWER_DISCONNECTED);

                context?.startService(DashboardActivity.mediaServiceIntent)

                Log.v("$$$$$$", "Power Disconnected")
                Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show();
            }
        }
    }
}