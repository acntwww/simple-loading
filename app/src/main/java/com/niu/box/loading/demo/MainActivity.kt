package com.niu.box.loading.demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.niu.box.loading.LoadingHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showLoading(view: View) {
        LoadingHandler.showLoading(this)
        Handler(Looper.getMainLooper()).postDelayed({
            LoadingHandler.hideLoading(this@MainActivity)
        }, 2000)
    }
}