# RKActivityResult
对 `startActivityForResult(intent,requestCode)` 方式打开 `Activity`，
重写 `onActivityResult(int requestCode, int resultCode, Intent data)` 方法获取数据的方
式进行封装，避免重写 `onActivityResult(int requestCode, int resultCode, Intent data)` 方法。
方便代码的编写。使用Kotlin语言。
