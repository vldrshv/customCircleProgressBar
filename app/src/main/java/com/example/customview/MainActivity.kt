package com.example.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import com.example.customview.Presenter
import com.example.customview.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IActivity {
    
    private val CLASS_TAG = "MainActivity"

    private var presenter: IPresenter = Presenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataButton.setOnClickListener { presenter.getInfoFromServer() }

    }

    override fun setProgress(progress: Float) {
        progressView.setProgress(progress)
        Log.i(CLASS_TAG, "$progress")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }


}
