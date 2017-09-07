package com.fengwenyi.servicetest

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * WenyiFeng(xfsy2014@gmail.com)
 * 2017-09-07 14:10
 */
class FirstService : Service() {

    private val TAG = "WYF_TAG"

    override fun onBind(p0: Intent?): IBinder {
        // TODO
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate() executed")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand() executed")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestory() executed")
    }
}