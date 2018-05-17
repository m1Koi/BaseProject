package com.m1ku.ktutils.ext

import java.io.Closeable

/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/5/17
 * Description:
 */
fun Closeable?.closeQuietly() {
    try {
        this?.close()
    } catch (e: Throwable) {
        // ignore
    }
}