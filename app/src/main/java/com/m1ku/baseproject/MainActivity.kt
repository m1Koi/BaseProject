package com.m1ku.baseproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.m1ku.ktutils.ext.hideKeyBoard
import com.m1ku.ktutils.ext.showKeyBoard
import com.m1ku.ktutils.ext.showToast
import com.m1ku.ktutils.util.FileUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvShow.setOnClickListener {
            etInput.showKeyBoard()
        }

        tvHide.setOnClickListener {
            it.hideKeyBoard()
        }
        showToast(FileUtils.checkSDCard().toString())
    }
}
