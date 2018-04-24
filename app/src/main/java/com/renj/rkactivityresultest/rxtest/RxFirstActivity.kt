package com.renj.rkactivityresultest.rxtest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.renj.rkactivityresult.rxjava.RKActivityRequest
import com.renj.rkactivityresult.rxjava.RKActivityResponse
import com.renj.rkactivityresult.rxjava.RKActivityResult
import com.renj.rkactivityresultest.R
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class RxFirstActivity : AppCompatActivity() {

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
            val intent = Intent(this@RxFirstActivity, RxSecondActivity::class.java)
            intent.putExtra("name", "从第一个页面打开第二个页面")
            RKActivityResult.create(this@RxFirstActivity)
                    .startActivityForResult(RKActivityRequest(1, intent))
                    .subscribe(object : Observer<RKActivityResponse> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(rkActivityResponse: RKActivityResponse) {
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(this@RxFirstActivity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }

                        override fun onError(e: Throwable) {

                        }

                        override fun onComplete() {
                            // 当返回结果的 Intent 为null时，将不会调用 onNext() 方法，只会调用 onComplete() 方法；否则两个方法都会调用
                            // Toast.makeText(RxFirstActivity.this, "调用了onComplete()方法", Toast.LENGTH_SHORT).show();
                        }
                    })
        }

        // 打开第三个Activity页面，并获取返回结果
        btOpenThreadActivity!!.setOnClickListener {
            val intent = Intent(this@RxFirstActivity, RxThreadActivity::class.java)
            intent.putExtra("name", "从第一个页面打开第三个页面")
            RKActivityResult.create(this@RxFirstActivity)
                    // 使用简单的方式打开，不传递requestCode
                    .startActivityForResult(intent)
                    .subscribe { rkActivityResponse ->
                        val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                        Toast.makeText(this@RxFirstActivity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                    }
        }

        // 打开Fragment页面，测试Fragment
        btOpenFragment!!.setOnClickListener {
            val intent = Intent(this@RxFirstActivity, RxMyFragmentActivity::class.java)
            startActivity(intent)
        }
    }
}
