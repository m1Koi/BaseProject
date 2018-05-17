package com.m1ku.ktutils.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/5/17
 * Description:
 */

/**
 * 显示输入法 view必须是一个Visible的EditText
 */
fun View.showKeyBoard() {
    requestFocus()
    val im = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.showSoftInput(this, 0)
}

/**
 * 隐藏输入法 view可以是任何一个view
 */
fun View.hideKeyBoard() {
    clearFocus()
    val im = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    im.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * View可见性的扩展属性
 */
val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

val View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE

val View.isGone: Boolean
    get() = visibility == View.GONE