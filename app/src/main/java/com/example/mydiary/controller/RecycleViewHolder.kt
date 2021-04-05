package com.example.mydiary.controller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

open class RecyclerViewHolder<T>(parent: ViewGroup, @LayoutRes resource:Int):RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(resource, parent, false)) {
    private var mData: T ?= null

    @CallSuper
    fun onBindView(data:T) {
        mData = data
    }

    fun getData(): T? {
        return mData
    }
}