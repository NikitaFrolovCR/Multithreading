package com.frolov.nikita.multithreading

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val callback = Handler.Callback { msg: Message? ->
        Toast.makeText(this, (msg?.obj as Int).toString(), Toast.LENGTH_SHORT).show()
        true
    }

    private lateinit var handler: Handler

    private lateinit var threadPoolExecutor: ThreadPoolExecutor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        handler = Handler(mainLooper, callback)

        val availableProcessors = Runtime.getRuntime().availableProcessors() + 1
        threadPoolExecutor = ThreadPoolExecutor(
                availableProcessors,
                availableProcessors,
                60L,
                TimeUnit.SECONDS,
                LinkedBlockingQueue<Runnable>()
        )

        val first = threadPoolExecutor.submit(FirstThread(handler))
        val second = threadPoolExecutor.submit(SecondThread(handler))
        threadPoolExecutor.execute(ThirdThread(first, second, handler))
    }

    override fun onDestroy() {
        threadPoolExecutor.shutdownNow()
        super.onDestroy()
    }
}
