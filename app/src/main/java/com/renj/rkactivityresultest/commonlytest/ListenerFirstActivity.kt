package com.renj.rkactivityresultest.commonlytest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.renj.rkactivityresult.commonly.RKActivityRequest
import com.renj.rkactivityresult.commonly.RKActivityResponse
import com.renj.rkactivityresult.commonly.RKActivityResult
import com.renj.rkactivityresultest.R


class ListenerFirstActivity : AppCompatActivity() {

    private var btOpenSecondActivity: Button? = null
    private var btOpenThreadActivity: Button? = null
    private var btOpenFragment: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        btOpenSecondActivity = findViewById(R.id.bt_open_second)
        btOpenThreadActivity = findViewById(R.id.bt_open_thread)
        btOpenFragment = findViewById(R.id.bt_open_fragment)

        // 打开第二个Activity页面，并获取返回结果
        btOpenSecondActivity!!.setOnClickListener {
            val intent = Intent(this@ListenerFirstActivity, ListenerSecondActivity::class.java)
            intent.putExtra("name", "从第一个页面打开第二个页面")
            RKActivityResult.create(this@ListenerFirstActivity)
                    .startActivityForResult(RKActivityRequest(1, intent), object : RKActivityResult.RKActivityResultListener() {
                        override fun onResult(rkActivityResponse: RKActivityResponse) {
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(this@ListenerFirstActivity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }

                        override fun onComplete(intentIsEmpty: Boolean) {
                            // 当返回结果的 Intent 为null时，将不会调用 onResult() 方法，只会调用 onComplete() 方法；否则两个方法都会调用
                            if (intentIsEmpty)
                                Toast.makeText(this@ListenerFirstActivity, "onComplete()方法intentIsEmpty为true：没有返回任何数据", Toast.LENGTH_SHORT).show()
                        }
                    })
        }

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity!!.setOnClickListener {
            val intent = Intent(this@ListenerFirstActivity, ListenerThreadActivity::class.java)
            intent.putExtra("name", "从第一个页面打开第三个页面")
            RKActivityResult.create(this@ListenerFirstActivity)
                    // 使用简单的方式打开，不传递requestCode
                    .startActivityForResult(intent, object : RKActivityResult.RKActivityResultListener() {
                        override fun onResult(rkActivityResponse: RKActivityResponse) {
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(this@ListenerFirstActivity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }
                    })
        }

        // 打开Fragment页面，测试Fragment
        btOpenFragment!!.setOnClickListener {
            val intent = Intent(this@ListenerFirstActivity, ListenerMyFragmentActivity::class.java)
            startActivity(intent)
        }
    }
}
