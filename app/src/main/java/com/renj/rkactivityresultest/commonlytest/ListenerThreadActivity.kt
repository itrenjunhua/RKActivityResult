package com.renj.rkactivityresultest.commonlytest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
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
class ListenerThreadActivity : AppCompatActivity() {
    private var btClose: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        val intent = intent
        val name = intent.getStringExtra("name")
        Toast.makeText(this@ListenerThreadActivity, "获取结果: $name", Toast.LENGTH_SHORT).show()

        btClose = findViewById(R.id.bt_close)

        // 关闭当前Activity并设置返回值
        btClose!!.setOnClickListener {
            val intent = Intent()
            intent.putExtra("resultName", "从第三个页面返回")
            setResult(0, intent)
            finish()
        }
    }
}
