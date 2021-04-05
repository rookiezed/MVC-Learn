package com.example.mydiary.controller

import android.view.View
import android.view.ViewGroup
import com.example.mydiary.R
import com.example.mydiary.model.Diary

class DiaryHolder(parent: ViewGroup): RecyclerViewHolder<Diary>(parent, R.layout.list_diary_item) {
    private lateinit var mOnLongClickListener: View.OnLongClickListener
    fun setOnLongClickListener(onLongClickListener: View.OnLongClickListener) {
        mOnLongClickListener = onLongClickListener
    }

}