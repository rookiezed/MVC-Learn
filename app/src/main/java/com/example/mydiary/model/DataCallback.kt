package com.example.mydiary.model

interface DataCallback<T> { // 定义回调接口

    fun onSuccess(data: T)  // 通知成功

    fun onError()           // 通知失败
}