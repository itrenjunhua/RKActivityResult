package com.renj.rkactivityresult.rxjava

import android.content.Intent
import io.reactivex.subjects.Subject

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2018-04-22   16:11
 *
 * 描述：代理Fragment接口
 *
 * 修订历史：
 *
 * ======================================================================
 */
interface IProxyFragment {
    /**
     * 设置Subject
     *
     * @param subject
     */
    fun setRKActivityResponseSubject(subject: Subject<RKActivityResponse>)

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