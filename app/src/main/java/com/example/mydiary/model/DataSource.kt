package com.example.mydiary.model


interface DataSource<T> {

    // 获取所有数据 T
    fun getAll(callback: DataCallback<List<T>>)

    // 获取某个数据 T
    fun get(id: String, callback: DataCallback<T>)

    // 更新某个数据 T
    fun update(diary: T)

    // 清空所有数据 T
    fun clear()

    // 删除某个数据 T
    fun delete(id: String)
}