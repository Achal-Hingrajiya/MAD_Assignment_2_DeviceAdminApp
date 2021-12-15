package com.deviceadminapp.models

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class LoginAttemptModel( var success : Boolean) {

    @RequiresApi(Build.VERSION_CODES.N)
    var attemptTime : String =  getCurrentDateTime()
    var id : Long = attemptIdGenerator()

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.N)
    private fun getCurrentDateTime(): String {
        val currentTimeInMillis: Long = System.currentTimeMillis()

        val df: SimpleDateFormat = SimpleDateFormat("dd MMM, yyyy hh:mm a")
        return df.format(Date(currentTimeInMillis))
    }

    companion object{

        var id : Long = 0
        var listOfAttempts = ArrayList<LoginAttemptModel>()

        fun attemptIdGenerator(): Long {
            id++
            return id
        }

        fun addAttempt(attempt: LoginAttemptModel){
            listOfAttempts.add(attempt)

        }
    }
}