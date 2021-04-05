package com.example.mydiary.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.collection.SimpleArrayMap

class SharedPreferenceUtils(context: Context) {
    private lateinit var mContext: Context
    private var mCaches: SimpleArrayMap<String, SharedPreferenceUtils> ? = null
    private lateinit var mSharedPrefences: SharedPreferences
    constructor(spName: String, mode: Int, context: Context) : this(context) {
        mSharedPrefences = context.getSharedPreferences(spName, mode)
        mContext = context
    }

    fun getInstance(spName: String): SharedPreferenceUtils {
        var spUtils = mCaches?.get(spName)
        if (spUtils == null) {
            spUtils = SharedPreferenceUtils(spName, Context.MODE_PRIVATE, mContext)
            mCaches?.put(spName, spUtils)
        }
        return spUtils
    }

    fun put(key: String, value: String){
        mSharedPrefences.edit().putString(key, value).apply()
    }

    fun get(key: String): String? {
        return mSharedPrefences.getString(key,"")
    }

    fun remove(key: String){
        return mSharedPrefences.edit().remove(key).apply()
    }
}