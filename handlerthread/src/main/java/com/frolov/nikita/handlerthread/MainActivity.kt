package com.frolov.nikita.handlerthread

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val workerThread = WorkerThread()

        val textView = findViewById<TextView>(R.id.tvText)

        findViewById<Button>(R.id.bClick).setOnClickListener {
            workerThread.postTask(Runnable {
                Thread.sleep(1000)
                textView.post { textView.text = textView.text.toString() + "1-" }
            })
        }

        workerThread.start()
        workerThread.prepareHandler()

    }
}
