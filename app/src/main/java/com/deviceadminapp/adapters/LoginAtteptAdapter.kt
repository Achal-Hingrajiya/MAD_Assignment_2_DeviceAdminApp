package com.deviceadminapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.deviceadminapp.R
import com.deviceadminapp.models.LoginAttemptModel


class LoginAtteptAdapter(private val context : Context) : BaseAdapter() {

    private val dbHelper = LoginAttemptsDbHelper(context)
    private var dataSource = dbHelper.getAllAttempts()

    override fun notifyDataSetChanged() {
        super.notifyDataSetChanged()
        dataSource = dbHelper.getAllAttempts()
    }
    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): LoginAttemptModel {
        return  dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return getItem(p0).id
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val attemptCard = inflater.inflate(R.layout.login_attempt_card, p2, false)

        val attemptStatus = attemptCard.findViewById<View>(R.id.v_success)
        val attemptTime = attemptCard.findViewById<TextView>(R.id.tv_attempt_time)

        val attempt = getItem(p0)

        attemptTime.text = attempt.attemptTime
        if (attempt.success != LoginAttemptModel.SUCCESS) attemptStatus.backgroundTintList = context.resources.getColorStateList(R.color.red)

        return attemptCard
    }
}