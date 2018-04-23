package com.renj.rkactivityresultest.commonlytest

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Button
import com.renj.rkactivityresultest.R


/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2018-04-06   20:58
 *
 *
 * 描述：作为Fragment的测试类
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
class ListenerMyFragmentActivity : FragmentActivity() {
    private var btV4Fragment: Button? = null
    private var btAppFragment: Button? = null
    private var v4Fragment: ListenerMyV4Fragment? = null
    private var appFragment: ListenerMyAppFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        btV4Fragment = findViewById(R.id.bt_v4_fragment)
        btAppFragment = findViewById(R.id.bt_app_fragment)

        v4Fragment = ListenerMyV4Fragment()
        appFragment = ListenerMyAppFragment()

        // 默认显示v4包下的
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_content, v4Fragment)
        fragmentTransaction.commit()

        // 显示v4包下的Fragment
        btV4Fragment!!.setOnClickListener {
            val supportFragmentManager = getSupportFragmentManager()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fl_content, ListenerMyV4Fragment())
            fragmentTransaction.commit()
        }

        // 显示app包下的Fragment
        btAppFragment!!.setOnClickListener {
            val fragmentManager = fragmentManager
            val fragmentTransaction1 = fragmentManager.beginTransaction()
            fragmentTransaction1.replace(R.id.fl_content, appFragment)
            fragmentTransaction1.commit()
        }
    }
}
