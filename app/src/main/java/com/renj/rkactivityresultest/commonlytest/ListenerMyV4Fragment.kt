package com.renj.rkactivityresultest.commonlytest

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

import com.renj.rkactivityresult.commonly.RKActivityRequest
import com.renj.rkactivityresult.commonly.RKActivityResponse
import com.renj.rkactivityresult.commonly.RKActivityResult
import com.renj.rkactivityresultest.R

/**
 * ======================================================================
 *
 *
 * 作者：Renj
 *
 *
 * 创建时间：2018-04-06   21:00
 *
 *
 * 描述：继承至v4支持包的Fragment
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
class ListenerMyV4Fragment : Fragment() {
    private var btOpenSecondActivity: Button? = null
    private var btOpenThreadActivity: Button? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_v4, null)
        btOpenSecondActivity = view.findViewById(R.id.bt_open_second)
        btOpenThreadActivity = view.findViewById(R.id.bt_open_thread)

        // 打开第二个Activity页面，并获取返回结果
        btOpenSecondActivity!!.setOnClickListener {
            val intent = Intent(activity, ListenerSecondActivity::class.java)
            intent.putExtra("name", "从v4包下Fragment页面打开第二个页面")
            RKActivityResult.create(activity!!)
                    .startActivityForResult(RKActivityRequest(1, intent), object : RKActivityResult.RKActivityResultListener() {
                        override fun onResult(rkActivityResponse: RKActivityResponse) {
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(activity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }
                    })
        }

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity!!.setOnClickListener {
            val intent = Intent(activity, ListenerThreadActivity::class.java)
            intent.putExtra("name", "从v4包下Fragment页面打开第三个页面")
            RKActivityResult.create(activity!!)
                    // 使用简单的方式打开，不传递requestCode
                    .startActivityForResult(intent, object : RKActivityResult.RKActivityResultListener() {
                        override fun onResult(rkActivityResponse: RKActivityResponse) {
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(activity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }
                    })
        }

        return view
    }
}
