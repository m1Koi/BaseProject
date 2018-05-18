package com.m1ku.ktutils.util

import android.app.Activity
import java.util.*

/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/3/12
 * Description: 将应用内Activity使用一个栈统一管理
 */
object ActivityManager {

    private val mActivities: Stack<Activity> = Stack()

    /**
     * 添加统一管理
     *
     * @param activity
     */
    fun attach(activity: Activity) {
        mActivities.add(activity)
    }

    /**
     * 移除解绑 - 防止内存泄漏
     *
     * @param detachActivity
     */
    fun detach(detachActivity: Activity) {
        var size = mActivities.size
        var i = 0
        while (i < size) {
            val activity = mActivities[i]
            if (activity === detachActivity) {
                mActivities.removeAt(i)
                i--
                size--
            }
            i++
        }
    }

    /**
     * 获取当前的Activity（最前面）
     *
     * @return
     */
    fun currentActivity(): Activity {
        return mActivities.lastElement()
    }

    /**
     * 关闭当前的 Activity
     *
     * @param finishActivity
     */
    fun finish(finishActivity: Activity) {
        var size = mActivities.size
        var i = 0
        while (i < size) {
            val activity = mActivities[i]
            if (activity === finishActivity) {
                mActivities.removeAt(i)
                activity.finish()
                i--
                size--
            }
            i++
        }
    }

    /**
     * 根据Activity的类名关闭 Activity
     */
    fun finish(activityClass: Class<out Activity>) {
        var size = mActivities.size
        var i = 0
        while (i < size) {
            val activity = mActivities[i]
            if (activity.javaClass.canonicalName == activityClass.canonicalName) {
                mActivities.removeAt(i)
                activity.finish()
                i--
                size--
            }
            i++
        }
    }

    fun finishAll() {
        var size = mActivities.size
        var i = 0
        while (i < size) {
            val activity = mActivities[i]
            mActivities.removeAt(i)
            activity.finish()
            i--
            size--
            i++
        }
    }

    /**
     * 退出整个应用
     */
    fun exitApp() {
        finishAll()
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }
}
