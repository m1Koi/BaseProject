package com.m1ku.ktutils.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Author: m1Ku
 * Email: howx172@163.com
 * Create Time: 2018/3/12
 * Description: 用于主页面Fragment切换控制,
 *              初始化时,传入fragmentManager和布局id,
 *              调用add方法添加第一个fragment,切换调用switch方法
 */
class MainFragmentController(
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) {
    /**
     * 添加Fragment
     */
    fun add(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment)
        fragmentTransaction.commit()
    }

    /**
     * 切换Fragment
     */
    fun switch(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragments = fragmentManager.fragments
        fragments.forEach { fragmentTransaction.hide(it) }

        if (!fragments.contains(fragment)) {
            fragmentTransaction.add(containerId, fragment)
        } else {
            fragmentTransaction.show(fragment)
        }
        fragmentTransaction.commitAllowingStateLoss() //修改了方法，后面查询确认
    }
}