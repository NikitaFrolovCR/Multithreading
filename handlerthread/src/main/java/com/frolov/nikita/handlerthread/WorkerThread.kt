package com.frolov.nikita.handlerthread

import android.os.Handler
import android.os.HandlerThread

class WorkerThread : HandlerThread(WorkerThread::class.java.simpleName) {

    private lateinit var workerHandler: Handler

    fun postTask(task: Runnable) {
        workerHandler.post(task)
    }

    fun prepareHandler() {
        workerHandler = Handler(looper)
    }

}