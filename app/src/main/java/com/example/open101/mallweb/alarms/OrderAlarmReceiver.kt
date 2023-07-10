package com.example.open101.mallweb.alarms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.open101.R
import com.example.open101.mallweb.db.DbMallweb


class OrderAlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (context != null && intent != null) {
            val dbMallweb = DbMallweb(context)
            if (dbMallweb.queryForOrder(intent.getIntExtra(context.getString(R.string.numOrder), 0)).state == context.getString(R.string.en_curso)) {
                dbMallweb.editStateOrder(intent.getIntExtra(context.getString(R.string.numOrder), 0), context.getString(R.string.abandonado))
            }
        }
    }
}