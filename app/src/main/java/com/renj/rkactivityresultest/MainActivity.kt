package com.renj.rkactivityresultest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.renj.rkactivityresultest.commonlytest.ListenerFirstActivity

class MainActivity : AppCompatActivity() {

    private var btRxMethod: Button? = null
    private var btListenerMethod: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btRxMethod = findViewById(R.id.bt_rx_method)
        btListenerMethod = findViewById(R.id.bt_listener_method)

        // 进入使用监听的方式测试页面
        btListenerMethod!!.setOnClickListener {
            val intent = Intent(MainActivity@this, ListenerFirstActivity::class.java)
            startActivity(intent)
        }
    }
}
