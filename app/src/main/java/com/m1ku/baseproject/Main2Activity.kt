package com.m1ku.baseproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.m1ku.ktutils.util.ActivityManager
import kotlinx.android.synthetic.main.activity_main.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        ActivityManager.attach(this)
//        tvJump.setOnClickListener {
//            startActivity(Intent(this, Main3Activity::class.java))
//        }
    }
}
