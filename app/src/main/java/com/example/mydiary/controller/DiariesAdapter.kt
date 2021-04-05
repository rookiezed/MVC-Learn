package com.example.mydiary.controller

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.model.Diary

class DiariesAdapter(diaries: List<Diary>): RecyclerView.Adapter<DiaryHolder>() {

    private var mDiaries: List<Diary> = diaries
    private lateinit var mOnLongClickListener: OnLongClickListener<Diary>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryHolder {
        return DiaryHolder(parent)
    }

    override fun onBindViewHolder(holder: DiaryHolder, position: Int) {
        val diary = mDiaries[position]
        holder.onBindView(diary)
        holder.setOnLongClickListener { v ->
            mOnLongClickListener != null && mOnLongClickListener.onLongClick(v, diary)
        }
    }

    override fun getItemCount(): Int {
        return mDiaries.size
    }

    fun update(diaries: List<Diary>) {
        mDiaries = diaries
        notifyDataSetChanged()
    }

    fun setOnLongClickListener(onLongClickListener: OnLongClickListener<Diary>) {
        mOnLongClickListener = onLongClickListener
    }

    interface OnLongClickListener<T> {
        fun onLongClick(v:View?, data:T):Boolean
    }
}