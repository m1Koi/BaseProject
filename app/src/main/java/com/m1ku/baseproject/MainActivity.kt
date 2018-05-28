package com.m1ku.baseproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.m1ku.ktutils.util.ActivityManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ActivityManager.attach(this)

        progressBar.setBottomText(listOf("0", "50", "100", "150"))
        progressBar.setTopText(listOf("2.0%", "2.5%", "3.0%"))
        progressBar.setMaxProgress(150f)
        progressBar.setCurrentProgress(100f)
//        tvShow.setOnClickListener {
//            etInput.showKeyBoard()
//        }
//
//        tvHide.setOnClickListener {
//            it.hideKeyBoard()
//        }
//        showToast(FileUtils.checkSDCard().toString())
//
//        tvToast.setOnClickListener {
//            showToast(ActivityManager.currentActivity().toString())
//        }
//
//        tvJump.setOnClickListener {
//            startActivity(Intent(this,Main2Activity::class.java))
//        }

    }
}
