package com.frolov.nikita.multithreading

import android.os.Handler
import java.util.concurrent.Future

class ThirdThread(private val first: Future<Int>,
                  private val second: Future<Int>,
                  private val handler: Handler) : Runnable {

    override fun run() {
        val result = first.get() + second.get()
        Thread.sleep(2000)
        handler.obtainMessage(0, result).sendToTarget()
    }
}