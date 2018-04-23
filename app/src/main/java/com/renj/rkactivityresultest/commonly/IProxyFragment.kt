package com.renj.rkactivityresult.commonly

import android.content.Intent

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2018-04-22   15:29
 *
 * 描述：代理Fragment接口
 *
 * 修订历史：
 *
 * ======================================================================
 */
interface IProxyFragment {
    /**
     * 设置监听
     *K
     * @param rkActivityResultListener [RKActivityResult.RKActivityResultListener] 对象
     */
    fun setRKActivityResultListener(rkActivityResultListener: RKActivityResult.RKActivityResultListener)

    /**
     * 调用 startActivityForResult() 方法，传递 [RKActivityRequest] 封装对象，包含 [Intent] 对象 和 requestCode
     *
     * @param RKActivityRequest [RKActivityRequest] 对象
     */
    fun startActivityForResult(RKActivityRequest: RKActivityRequest)

    /**
     * 调用 startActivityForResult() 方法，传递 [Intent] 对象
     *
     * @param intent [Intent] 对象
     */
    fun startActivityForResult(intent: Intent)
}