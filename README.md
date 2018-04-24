# RKActivityResult
对 `startActivityForResult(intent,requestCode)` 方式打开 `Activity`，
重写 `onActivityResult(int requestCode, int resultCode, Intent data)` 方法获取数据的方
式进行封装，避免重写 `onActivityResult(int requestCode, int resultCode, Intent data)` 方法。
方便代码的编写。使用Kotlin语言。


## 两种形式
> 1. 使用RxJava实现，返回一个Observable对象
> 2. 普通的形式，使用回调的方式获取结果

## 具体使用示例
### RxJava的形式使用示例
> 1.使用封装类传递Intent和requestCode

    btOpenSecondActivity!!.setOnClickListener {
                val intent = Intent(activity, RxSecondActivity::class.java)
                intent.putExtra("name", "从v4包下Fragment页面打开第二个页面")
                RKActivityResult.create(activity!!)
                        .startActivityForResult(RKActivityRequest(1, intent))
                        .subscribe { rkActivityResponse ->
                            val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                            Toast.makeText(activity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                        }
            }

> 2.不使用封装类，只传递Intent对象

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

##### **使用RxJava的形式需要注意一点：当返回结果 `Intent` 为 `null` 时，将不会调用 `onNext(rkActivityResponse : RKActivityResponse)` 方法，只会调用 `onComplete()` 方法；否则两个方法都会调用。**

---


### 普通使用回调形式使用示例
> 1.使用封装类传递Intent和requestCode

    btOpenSecondActivity!!.setOnClickListener {
                val intent = Intent(activity, ListenerSecondActivity::class.java)
                intent.putExtra("name", "从app包下Fragment页面打开第二个页面")
                RKActivityResult.create(activity)
                        .startActivityForResult(RKActivityRequest(1, intent), object : RKActivityResult.RKActivityResultListener() {
                            override fun onResult(rkActivityResponse: RKActivityResponse) {
                                val resultName = rkActivityResponse.responseIntent.getStringExtra("resultName")
                                Toast.makeText(activity, "返回结果: $resultName", Toast.LENGTH_SHORT).show()
                            }
                        })
            }

> 2.不使用封装类，只传递Intent对象

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

##### **使用普通回调监听的形式需要注意一点：不管返回结果 `Intent` 是否为 `null` ， `onResult(rkActivityResponse : RKActivityResponse)` 方法 和 `(intentIsEmpty: Boolean)` 方法都会调用；只是 `onComplete()` 方法会有一个参数 `intentIsEmpty` 标识 `Intent` 是否为 `null`。`onComplete(intentIsEmpty: Boolean)` 方法可以根据需求判断是否需要重写。**

---

#### 最后，如果需要拿到返回结果，我们必须在新打开的页面里调用 `setResult(int resultCode, Intent data)` 方法设置结果。
例：

    val intent = Intent()
    intent.putExtra("resultName", "从第三个页面返回")
    setResult(0, intent)
    finish()
