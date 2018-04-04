package com.github.lhoyong.transparencyseekbar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var p = 120

        tran.progress = p
        tran.maxProgress = 240


    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}
