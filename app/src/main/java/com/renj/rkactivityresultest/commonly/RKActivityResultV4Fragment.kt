package com.renj.rkactivityresult.commonly

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import java.util.*

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2018-04-06   18:05
 *
 *
 * 描述：继承至 [Fragment] 类
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
class RKActivityResultV4Fragment : Fragment(), IProxyFragment {
    companion object {
        private val TAG = RKActivityResultV4Fragment::class.java.name
    }

    private var rkActivityResultListener: RKActivityResult.RKActivityResultListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (rkActivityResultListener == null) {
            Log.i(TAG, "没有设置 RKActivityResult.rkActivityResultListener !!!")
            return
        }

        if (data != null)
            rkActivityResultListener!!.onResult(RKActivityResponse(requestCode, resultCode, data))
        rkActivityResultListener!!.onComplete(data == null)
    }

    override fun setRKActivityResultListener(rActivityResultListener: RKActivityResult.RKActivityResultListener) {
        this.rkActivityResultListener = rActivityResultListener
    }

    override fun startActivityForResult(RKActivityRequest: RKActivityRequest) {
        startActivityForResult(RKActivityRequest.requestIntent, RKActivityRequest.requestCode)
    }

    override fun startActivityForResult(intent: Intent) {
        // 使用 intent.hashCode() 方式报错：java.lang.IllegalArgumentException: Can only use lower 16 bits for requestCode
        // startActivityForResult(intent, intent.hashCode());
        val random = Random()
        val requestCode = random.nextInt(12500) + random.nextInt(25000)
        startActivityForResult(intent, requestCode)
    }
}
