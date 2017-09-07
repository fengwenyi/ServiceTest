package com.fengwenyi.servicetest

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.fengwenyi.servicetest.R.mipmap.ic_launcher
import com.fengwenyi.servicetest.R.mipmap.ic_launcher
import android.app.NotificationManager
import android.content.Context
import android.os.Process


/**
 * WenyiFeng(xfsy2014@gmail.com)
 * 2017-09-06 14:22
 */
class MyService : Service() {

    private val TAG = "WYF_TAG"

    private val mBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate() executed")
        Log.i("TAG", "process id is ${Process.myPid()}")

        Thread.sleep(1000)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand() executed")

        Thread(Runnable {
            kotlin.run {
                // 开始执行后台任务
            }
        }).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestory() executed")
        /**
         * 我们应该始终记得在Service的onDestroy()方法里去清理掉那些不再使用的资源，
         * 防止在Service被销毁后还会有一些不再使用的对象仍占用着内存。
         */

    }

    class MyBinder : Binder() {
        fun startDownload() {
            Log.i("TAG", "startDownload() executed")

            Thread(Runnable {
                kotlin.run {
                    // 执行具体的下载任务
                }
            }).start()
        }
    }
}