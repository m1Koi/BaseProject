package com.m1ku.baseproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.m1ku.ktutils.ext.hideKeyBoard
import com.m1ku.ktutils.ext.showKeyBoard
import com.m1ku.ktutils.ext.showToast
import com.m1ku.ktutils.util.ActivityManager
import com.m1ku.ktutils.util.FileUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityManager.attach(this)

        tvShow.setOnClickListener {
            etInput.showKeyBoard()
        }

        tvHide.setOnClickListener {
            it.hideKeyBoard()
        }
        showToast(FileUtils.checkSDCard().toString())

        tvToast.setOnClickListener {
            showToast(ActivityManager.currentActivity().toString())
        }

        tvJump.setOnClickListener {
            startActivity(Intent(this,Main2Activity::class.java))
        }

    }
}
