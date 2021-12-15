package com.deviceadminapp.adapters

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.deviceadminapp.models.LoginAttemptModel

class LoginAttemptsDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "DevAdminApp"
        private const val TABLE_ATTEMPTS = "ATTEMPTS"
        private const val KEY_ID = "id"
        private const val KEY_ATTEMPT_TIME = "attempt_time"
        private const val KEY_ATTEMPT_SUCCESS = "success"
    }

        override fun onCreate(db: SQLiteDatabase?) {

            val QUERY: String = (
                    "CREATE TABLE $TABLE_ATTEMPTS " +
                            "($KEY_ID INTEGER PRIMARY KEY, " +
                            "$KEY_ATTEMPT_SUCCESS INTEGER, " +
                            "$KEY_ATTEMPT_TIME TEXT)"
                    )
            db?.execSQL( "CREATE TABLE $TABLE_ATTEMPTS ($KEY_ID INTEGER PRIMARY KEY, $KEY_ATTEMPT_SUCCESS INTEGER, $KEY_ATTEMPT_TIME TEXT)")

        }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ATTEMPTS")
        onCreate(db)
    }

    fun insertAttempt(loginAttempt: LoginAttemptModel): Long {

        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(KEY_ID, loginAttempt.id)
        contentValues.put(KEY_ATTEMPT_SUCCESS, loginAttempt.success)
        contentValues.put(KEY_ATTEMPT_TIME, loginAttempt.attemptTime)

        val success = db.insert(TABLE_ATTEMPTS, null, contentValues)
        db.close()

        return success
    }

}