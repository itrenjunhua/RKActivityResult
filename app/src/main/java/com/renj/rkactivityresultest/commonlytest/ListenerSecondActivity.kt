package com.renj.rkactivityresultest.commonlytest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
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
 * 创建时间：2018-04-05   22:46
 *
 *
 * 描述：
 *
 *
 * 修订历史：
 *
 *
 * ======================================================================
 */
class ListenerSecondActivity : AppCompatActivity() {
    private var btClose: Button? = null
    private var btOpenThreadActivity: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val intent = intent
        val name = intent.getStringExtra("name")
        Toast.makeText(this@ListenerSecondActivity, "获取结果: $name", Toast.LENGTH_SHORT).show()

        btClose = findViewById(R.id.bt_close)
        btOpenThreadActivity = findViewById(R.id.bt_open_thread)

        // 关闭当前Activity并设置返回值
        btClose!!.setOnClickListener {
            val intent = Intent()
            intent.putExtra("resultName", "从第二个页面返回")
            setResult(0, intent)
            finish()
        }


        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity!!.setOnClickListener {
            val intent = Intent(this@ListenerSecondActivity, ListenerThreadActivity::class.java)
            intent.putExtra("name", "从第二个页面打开第三个页面")
            RKActivityResult.create(this@ListenerSecondActivity)
                    // 使用简单的方式打开，不传递requestCode
                    .startActivityForResult(intent, object : RKActivityResult.RKActivityResultListener() {
                        override fun onResult(rkActivityResponse: RKActivityResponse) {
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(this@ListenerSecondActivity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }
                    })
        }
    }
}
