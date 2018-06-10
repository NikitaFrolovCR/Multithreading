package com.frolov.nikita.multithreading

import android.os.Handler
import java.lang.Thread.sleep
import java.util.concurrent.Callable

class FirstThread(private val handler: Handler) : Callable<Int> {

    override fun call(): Int {
        sleep(2000)
        handler.obtainMessage(0, 1).sendToTarget()
        return 1
    }

}