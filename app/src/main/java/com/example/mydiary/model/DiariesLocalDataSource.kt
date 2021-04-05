package com.example.mydiary.model

import android.content.Context
import com.example.mydiary.MockDiaries
import com.example.mydiary.utils.SharedPreferenceUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class DiariesLocalDataSource(context: Context): DataSource<Diary> {



    private var LOCAL_DATA: MutableMap<String, Diary> ?= null

    private val DIARRY_DATA = "diary_data"
    private val ALL_DIARY = "all_diary"
    private var mSpUtils: SharedPreferenceUtils
    init {
        getContext(context)
        mSpUtils = SharedPreferenceUtils(context).getInstance(DIARRY_DATA)
        val diaryStr = mSpUtils.get(ALL_DIARY)
        val typeOf =
            object : TypeToken<LinkedHashMap<String, Diary>>() {}.type
        val diariesObj = Gson().fromJson<LinkedHashMap<String, Diary>>(diaryStr, typeOf)
        if (diariesObj.isNullOrEmpty()) {
            LOCAL_DATA = MockDiaries.mock()
        } else {
            LOCAL_DATA = diariesObj
        }
    }

    companion object {
        private lateinit var mContext: Context

        @Volatile private var mInstance: DiariesLocalDataSource? = null
        fun get(): DiariesLocalDataSource?{
            if (mInstance == null) { // 线程安全的单例模式
                synchronized (DiariesLocalDataSource::class.java) {
                    if (mInstance == null)
                    {
                        mInstance = DiariesLocalDataSource(mContext)
                    }
                }
            }
            return mInstance

        }
        fun getContext(context: Context){
            mContext = context
        }
    }

    override fun update(diary: Diary) {
        LOCAL_DATA?.put(diary.getId(), diary)
        mSpUtils.put(ALL_DIARY, Gson().toJson(LOCAL_DATA))
    }

    override fun clear() {
        LOCAL_DATA?.clear()
        mSpUtils.remove(ALL_DIARY)
    }

    override fun delete(id: String) {
        LOCAL_DATA?.remove(id)
        mSpUtils.put(ALL_DIARY, Gson().toJson(LOCAL_DATA))
    }

    override fun getAll(callback: DataCallback<List<Diary>>) {
        if (LOCAL_DATA.isNullOrEmpty()) {
            callback.onError()
        } else {
            val arrayList = ArrayList(LOCAL_DATA!!.values)
            callback.onSuccess(arrayList)
        }
    }

    override fun get(id: String, callback: DataCallback<Diary>) {
        val diary  = LOCAL_DATA?.get(id)
        if (diary != null) {
            callback.onSuccess(diary)
        } else {
            callback.onError()
        }
    }
}