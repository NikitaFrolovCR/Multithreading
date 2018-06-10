package com.frolov.nikita.multithreading

import android.os.Handler
import java.lang.Thread.sleep
import java.util.concurrent.Callable

class SecondThread(private val handler: Handler) : Callable<Int> {

    override fun call(): Int {
        sleep(4000)
        handler.obtainMessage(0, 2).sendToTarget()
        return 2
    }

}