
# 学习Android Service

> 首先要说明的是，本文是学习，并非原作者，源地址[Android Service完全解析，关于服务你所需知道的一切(上)（http://blog.csdn.net/guolin_blog/article/details/11952435/）]

1、Service的基本用法

2、Service和Activity的交互

3、Service 和 Thread

4、远程Service

5、跨进程通信

【待完善。。。】

### 〈一〉Service
我们首先来看下Service的生命周期
![Service生命周期图解](http://images.cnitblog.com/blog/325852/201303/24233205-ccefbc4a326048d79b111d05d1f8ff03.png)

从生命周期，就可以清楚的认识Service了，不过我们还是来写一个Service：
```
class FirstService : Service() {
    override fun onBind(p0: Intent?): IBinder {
        // TODO
    }
}
```
以上是一个最基础的Service代码（伪代码），我猜测onBind()可能就是写代码逻辑的方法。我们继续：
```
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
```
这段代码，可以帮我们很清楚知道Service的启动过程，到这里也算是和Service认识了吧。

### 〈二〉Service和Activity的交互
Service可以帮我们处理一些耗时操作（友好交互），那么我们怎么利用Activity来操作Service呢？

这写案例之前，我觉得有必要说一些方法：
```
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
```
从上面部分代码我们可以知道一些在Activity中操作Service的方法了，那么怎么让Activity和Service真正通信呢？看如下代码片段

这是一个MyService的内部类，来实现下载任务：
```
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
```
我们在MyService中使用，并进行返回：
```
	private val mBinder = MyBinder()

    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }
```
在Activity中调用：
```
	lateinit var myBinder : MyService.MyBinder

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            myBinder = service as MyService.MyBinder
            myBinder.startDownload()

        }

    }
```
### 〈三〉Service 和 Thread
博文中，提到Service和Thread的关系，这里简单总结下：Service和Thread没有关系。

我的理解：Service(后台)，是实现友好交互的一种方式，而Thread是开启多个线程来协同完成一项或多项工作。

到这里，今天的Service也就结束了，那么你会问了，远程Service和跨进程通信呢？

这里解释一下，远程Service博文也说了，不推荐，所以就不说了。至于跨进程通信和其他一些没有提及的，都会在后面慢慢补充和完善。

###### About me
```
WenyiFeng(xfsy2014@gmail.com)

Copyright © 2017 fengwenyi.com. All Rights Reserved.
```