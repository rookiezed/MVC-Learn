package com.example.mydiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mydiary.utils.ActivityUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun initFragment(){
        var diariesFragment: DiariesFragment = getDiariesFragment()
        if (diariesFragment == null){
            diariesFragment = DiariesFragment()
            // 将日记 fragment 加在 activity 下
            ActivityUtils.addFragmentToActivity(supportFragmentManager, diariesFragment, R.id.content)
        }
    }

    private fun getDiariesFragment(): DiariesFragment{
        return supportFragmentManager.findFragmentById(R.id.content) as DiariesFragment
    }
}