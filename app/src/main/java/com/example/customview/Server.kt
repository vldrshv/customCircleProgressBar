package com.example.customview

import android.annotation.SuppressLint
import android.util.Log
import kotlin.random.Random
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

class Server {
    private val CLASS_TAG = "Server"
    lateinit var observable: Observable<Float>

    private var thread: Thread = Thread(Runnable {
        while (true) {
            byte = Random.nextInt(0, 10).toFloat()
//            Log.i(CLASS_TAG, "$byte")
            Thread.sleep(200)
        }
    })
    private var byte: Float = 0f

    init {
        createObservable()
        thread.start()
    }

    @SuppressLint("CheckResult")
    private fun createObservable() {
        observable = Observable.create(ObservableOnSubscribe
        {
            try {
                while (true) {
                    it.onNext(byte)
                    Thread.sleep(1000)
                }
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        })
    }
}