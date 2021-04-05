package com.example.mydiary.utils

import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ActivityUtils {
    companion object{
        fun addFragmentToActivity(
            @NonNull fragmentManager: FragmentManager,
            @NonNull fragment: Fragment,
            frameId: Int
        ){
            // Fragment 事务开始
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}