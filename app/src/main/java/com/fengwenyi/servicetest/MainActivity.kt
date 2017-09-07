package com.fengwenyi.servicetest

import android.content.Intent
import android.content.ServiceConnection
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.os.IBinder
import android.content.ComponentName
import android.content.Context
import android.os.Process
import android.util.Log


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var myBinder : MyService.MyBinder

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBinder = service as MyService.MyBinder
            myBinder.startDownload()

        }

    }

    override fun onClick(view: View?) {
        var intent : Intent
        when(view?.id) {
            /** 开启Service */
            R.id.mainStartService -> {
                intent = Intent(this, MyService::class.java)
                startService(intent)
            }
            /** 停止Service */
            R.id.mainStopService -> {
                intent = Intent(this, MyService::class.java)
                stopService(intent)
            }
            /** Bind Service */
            R.id.mainBindService -> {
                intent = Intent(this, MyService::class.java)
                bindService(intent, connection, Context.BIND_AUTO_CREATE)
            }
            /** Unbind Service */
            R.id.mainUnbindService -> {
                unbindService(connection)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("TAG", "process id is ${Process.myPid()}")

        mainStartService.setOnClickListener(this)
        mainStopService.setOnClickListener(this)

        mainBindService.setOnClickListener(this)
        mainUnbindService.setOnClickListener(this)
    }
}
