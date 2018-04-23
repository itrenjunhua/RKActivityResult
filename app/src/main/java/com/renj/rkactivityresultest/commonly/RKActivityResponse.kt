package com.renj.rkactivityresult.commonly

import android.content.Intent

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2018-04-05   17:53
 *
 *
 * 描述：返回结果数据封装类
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
class RKActivityResponse(
        /**
         * 打开时传递的请求码，用于区分不同的请求。<br></br>
         * 当调用的是 [RKActivityResult.Builder.startActivityForResult] 方法打开新界面时不需要做判断
         */
        var requestCode: Int,
        /**
         * 结果码，新打开界面调用 [android.app.Activity.setResult]/[android.app.Activity.setResult] 方法设置返回结果时传递的 int 类型的值
         */
        var resultCode: Int,
        /**
         * 结果 [Intent] 对象，新打开界面调用 [android.app.Activity.setResult] 方法设置返回结果时传递的 [Intent] 对象
         */
        var responseIntent: Intent)
