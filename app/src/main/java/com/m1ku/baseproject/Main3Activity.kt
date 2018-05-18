package com.m1ku.baseproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.m1ku.ktutils.util.ActivityManager
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        ActivityManager.attach(this)
        tvJump.setOnClickListener {
            ActivityManager.finish(this)
            ActivityManager.finish(Main2Activity::class.java)
        }
    }
}
