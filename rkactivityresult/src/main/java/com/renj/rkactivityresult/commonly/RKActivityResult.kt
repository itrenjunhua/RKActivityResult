package com.renj.rkactivityresult.commonly

import android.app.Activity
import android.content.Intent
import android.support.v4.app.FragmentActivity


/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2018-04-05   18:26
 *
 *
 * 描述：对外提供的操作类
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
object RKActivityResult {
    private val TAG = "RKActivityResult_Commonly"

    fun create(activity: Activity): Builder {
        return when (activity) {
            is FragmentActivity -> Builder(activity)
            is Activity -> Builder(activity)
            else -> throw IllegalArgumentException("Activity 参数异常!!!")
        }
    }

    class Builder {
        private var iProxyFragment: IProxyFragment? = null

        constructor(activity: Activity) {
            iProxyFragment = getRActivityResultFragment(activity)
        }

        constructor(fragmentActivity: FragmentActivity) {
            iProxyFragment = getRActivityResultV4Fragment(fragmentActivity)
        }

        //------------------------ app包 ---------------------//
        private fun getRActivityResultFragment(activity: Activity): RKActivityResultFragment {
            var rkActivityResultFragment: RKActivityResultFragment? = findRActivityResultFragment(activity)
            val isNewInstance = (rkActivityResultFragment == null)
            if (isNewInstance) {
                rkActivityResultFragment = RKActivityResultFragment()
                val fragmentManager = activity.fragmentManager
                fragmentManager
                        .beginTransaction()
                        .add(rkActivityResultFragment, TAG)
                        .commitAllowingStateLoss()
                fragmentManager.executePendingTransactions()
            }
            return rkActivityResultFragment!!
        }

        private fun findRActivityResultFragment(activity: Activity): RKActivityResultFragment {
            return activity.fragmentManager.findFragmentByTag(TAG) as RKActivityResultFragment
        }

        //------------------------ v4包 ---------------------//
        private fun getRActivityResultV4Fragment(fragmentActivity: FragmentActivity): RKActivityResultV4Fragment {
            var rkActivityResultV4Fragment: RKActivityResultV4Fragment? = findRActivityResultV4Fragment(fragmentActivity)
            val isNewInstance = (rkActivityResultV4Fragment == null)
            if (isNewInstance) {
                rkActivityResultV4Fragment = RKActivityResultV4Fragment()
                val supportFragmentManager = fragmentActivity.supportFragmentManager
                supportFragmentManager
                        .beginTransaction()
                        .add(rkActivityResultV4Fragment, TAG)
                        .commitAllowingStateLoss()
                supportFragmentManager.executePendingTransactions()
            }
            return rkActivityResultV4Fragment!!
        }

        private fun findRActivityResultV4Fragment(fragmentActivity: FragmentActivity): RKActivityResultV4Fragment {
            return fragmentActivity.supportFragmentManager.findFragmentByTag(TAG) as RKActivityResultV4Fragment
        }

        /**
         * 以 startActivityForResult() 的方式打开新的Activity，参数只传递 [Intent] 对象，在监听的回调中不需要对 requestCode 进行判断
         *
         * @param intent                  [Intent] 对象
         * @param RKActivityResultListener 回调监听 [RKActivityResultListener] 对象
         */
        fun startActivityForResult(intent: Intent, RKActivityResultListener: RKActivityResultListener) {
            iProxyFragment!!.setRKActivityResultListener(RKActivityResultListener)
            iProxyFragment!!.startActivityForResult(intent)
        }

        /**
         * 以 startActivityForResult() 的方式打开新的Activity，参数传递[RKActivityRequest]对象，在监听的回调中根据需要对 requestCode 进行判断，一般情况下都不需要
         *
         * @param RKActivityRequest        [RKActivityRequest] 对象
         * @param RKActivityResultListener 回调监听 [RKActivityResultListener] 对象
         */
        fun startActivityForResult(RKActivityRequest: RKActivityRequest, RKActivityResultListener: RKActivityResultListener) {
            iProxyFragment!!.setRKActivityResultListener(RKActivityResultListener)
            iProxyFragment!!.startActivityForResult(RKActivityRequest)
        }
    }

    /**
     * 返回结果之后的回调监听
     */
    abstract class RKActivityResultListener {
        /**
         * [Intent] 不为 `null` 的时候才会回调的方法
         *
         * @param RKActivityResponse [RKActivityResponse] 对象，回调了这个方法 [RKActivityResponse.responseIntent] 不会为 `null` 了
         */
        abstract fun onResult(RKActivityResponse: RKActivityResponse)

        /**
         * 一定会回调的方法，表示完成。这个方法主要是在不需要传递数据，只需要新打开的界面关闭了然后做其他操作时重写即可
         *
         * @param intentIsEmpty [RKActivityResponse.responseIntent] 是否为 `null`，true：为`null`；false：非 `null`
         */
        fun onComplete(intentIsEmpty: Boolean) {

        }
    }
}
