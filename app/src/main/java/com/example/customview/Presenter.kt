package com.example.customview

import android.annotation.SuppressLint
import android.util.Log

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class Presenter(val view: IActivity) : IPresenter {
    private val CLASS_TAG = "Presenter"
    private var progress: Float = 0f
    private lateinit var server: Server
    private lateinit var disposable: Disposable

    @SuppressLint("CheckResult")
    fun createObserver() {
        server.observable
            .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    { it: Float ->
                        progress = it.toFloat()
                        if (progress < 100)
                            updateProgress()
                        else
                            unsubscribe()
                    },
                    { e: Throwable -> e.printStackTrace() },
                    { Log.i(CLASS_TAG, "COMPLETED") },
                    { t: Disposable? -> disposable = t!! }
                )
    }

    override fun getInfoFromServer() {
        server = Server()
        createObserver()
    }

    override fun unsubscribe() {
        disposable.dispose()
    }

    private fun updateProgress() = view.setProgress(progress)

}