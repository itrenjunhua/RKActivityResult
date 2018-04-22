package com.renj.rkactivityresult.rxjava

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * ======================================================================
 *
 * 作者：Renj
 *
 * 创建时间：2018-04-22   16:21
 *
 * 描述：对外提供的操作类
 *
 * 修订历史：
 *
 * ======================================================================
 */
object RKActivityResult {
    private val TAG = "RKActivityResult_RxJava"

    fun create(activity: Activity): Builder {
        return when (activity) {
            is FragmentActivity -> Builder(activity)
            is Activity -> Builder(activity)
            else -> throw IllegalArgumentException("Activity 参数异常!!!")
        }
    }


    class Builder {
        private var subject: PublishSubject<RKActivityResponse>? = null
        private var iProxyFragment: IProxyFragment? = null

        constructor(activity: Activity) {
            iProxyFragment = getRxActivityResultFragment(activity)
            subject = PublishSubject.create<RKActivityResponse>()
            iProxyFragment!!.setRKActivityResponseSubject(subject!!)
        }

        constructor(fragmentActivity: FragmentActivity) {
            iProxyFragment = getRxActivityResultV4Fragment(fragmentActivity)
            subject = PublishSubject.create()
            iProxyFragment!!.setRKActivityResponseSubject(subject!!)
        }

        //------------------------ app包 ---------------------//
        private fun getRxActivityResultFragment(activity: Activity): RxKActivityResultFragment {
            var rxKActivityResultFragment: RxKActivityResultFragment? = findRxActivityResultFragment(activity)
            val isNewInstance = rxKActivityResultFragment == null
            if (isNewInstance) {
                rxKActivityResultFragment = RxKActivityResultFragment()
                val fragmentManager = activity.fragmentManager
                fragmentManager
                        .beginTransaction()
                        .add(rxKActivityResultFragment, TAG)
                        .commitAllowingStateLoss()
                fragmentManager.executePendingTransactions()
            }
            return rxKActivityResultFragment!!
        }

        private fun findRxActivityResultFragment(activity: Activity): RxKActivityResultFragment {
            return activity.fragmentManager.findFragmentByTag(TAG) as RxKActivityResultFragment
        }

        //------------------------ v4包 ---------------------//
        private fun getRxActivityResultV4Fragment(fragmentActivity: FragmentActivity): RxKActivityResultV4Fragment {
            var rxKActivityResultV4Fragment: RxKActivityResultV4Fragment? = findRxActivityResultV4Fragment(fragmentActivity)
            val isNewInstance = rxKActivityResultV4Fragment == null
            if (isNewInstance) {
                rxKActivityResultV4Fragment = RxKActivityResultV4Fragment()
                val supportFragmentManager = fragmentActivity.supportFragmentManager
                supportFragmentManager
                        .beginTransaction()
                        .add(rxKActivityResultV4Fragment, TAG)
                        .commitAllowingStateLoss()
                supportFragmentManager.executePendingTransactions()
            }
            return rxKActivityResultV4Fragment!!
        }

        private fun findRxActivityResultV4Fragment(fragmentActivity: FragmentActivity): RxKActivityResultV4Fragment {
            return fragmentActivity.supportFragmentManager.findFragmentByTag(TAG) as RxKActivityResultV4Fragment
        }

        /**
         * 以 startActivityForResult() 的方式打开新的Activity，参数只传递 [Intent] 对象，在监听的回调中不需要对 requestCode 进行判断
         *
         * @param intent [Intent] 对象
         */
        fun startActivityForResult(intent: Intent): Observable<RKActivityResponse> {
            iProxyFragment!!.startActivityForResult(intent)
            return subject!!
        }

        /**
         * 以 startActivityForResult() 的方式打开新的Activity，参数传递[RActivityRequest]对象，在监听的回调中根据需要对 requestCode 进行判断，一般情况下都不需要
         *
         * @param rActivityRequest [RActivityRequest] 对象
         */

        fun startActivityForResult(rActivityRequest: RKActivityRequest): Observable<RKActivityResponse> {
            iProxyFragment!!.startActivityForResult(rActivityRequest)
            return subject!!
        }
    }
}