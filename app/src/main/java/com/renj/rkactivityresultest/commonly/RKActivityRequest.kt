package com.renj.rkactivityresult.commonly

import android.content.Intent

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2018-04-05   17:52
 *
 *
 * 描述：请求数据封装类
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
class RKActivityRequest(
        /**
         * 请求码
         */
        var requestCode: Int,
        /**
         * 请求的 [Intent] 对象
         */
        var requestIntent: Intent)
