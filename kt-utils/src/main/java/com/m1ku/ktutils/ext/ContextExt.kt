package com.m1ku.ktutils.ext

import android.content.Context
import android.content.pm.PackageManager
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.widget.Toast
import android.util.TypedValue



/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/5/17
 * Description: Context的扩展函数
 */

/**
 *  获取版本名字
 */
fun Context.getVersionName(): String {
    val manager = applicationContext.packageManager
    try {
        val packageInfo = manager.getPackageInfo(applicationContext.packageName, 0)
        packageInfo?.let {
            return packageInfo.versionName
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return ""
}

/**
 *  获取版本号
 */
fun Context.getVersionCode(): Int {
    val manager = applicationContext.packageManager
    try {
        val packageInfo = manager.getPackageInfo(applicationContext.packageName, 0)
        packageInfo?.let {
            return packageInfo.versionCode
        }
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }
    return 0
}

/**
 *  显示吐司
 */

var mToast: Toast? = null

fun Context.showToast(msg: String, duration: Int = Toast.LENGTH_LONG) {

    if (mToast == null) {
        mToast = Toast.makeText(this, msg, duration)
    } else {
        mToast?.duration = duration
        mToast?.setText(msg)
    }
    mToast?.show()
}

/**
 * dp转为px
 */
fun Context.dp2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.dp2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()


/**
 * px转为dp
 */
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density

/**
 * return sp dimension value in pixels
 * @param value sp
 */
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()


/**
 * 获取颜色
 */
fun Context.color(@ColorRes id: Int): Int {
    return ContextCompat.getColor(this, id)
}

