package com.example.mydiary.controller

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.DiariesFragment
import com.example.mydiary.model.DataCallback
import com.example.mydiary.model.DiariesRepository
import com.example.mydiary.model.Diary

class DiariesController(diariesFragment: DiariesFragment) {
    val mDiariesRepository = DiariesRepository.getInstance()
    private lateinit var mListAdapter: DiariesAdapter
    var mView = diariesFragment
    init {
        mView.setHasOptionsMenu(true)
        initAdapter()
    }

    private fun initAdapter() {
        mListAdapter = DiariesAdapter(ArrayList())
        mListAdapter.setOnLongClickListener(object : DiariesAdapter.OnLongClickListener<Diary>{
            override fun onLongClick(v: View?, data: Diary): Boolean {
                showInputDialog(data)
                return false
            }

        })
    }

    private fun showInputDialog(data: Diary) {
        val editText: EditText = EditText(mView.context)
        editText.setText(data.getDescription())
        val alertDialog = AlertDialog.Builder(mView.requireContext())
            .setTitle(data.getTitle())
            .setView(editText)
            .setPositiveButton("确定"
            ) { _, _ ->
                data.setDescription(editText.text.toString())
                mDiariesRepository.update(data)
                loadDiaries()
            }
            .setNegativeButton("取消", null)
        alertDialog.show()

    }

    fun setDiariesList(recyclerView: RecyclerView) {
        recyclerView.layoutManager = LinearLayoutManager(mView.requireContext())
        recyclerView.adapter = mListAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(mView.requireContext(), DividerItemDecoration.VERTICAL))
        recyclerView.itemAnimator = DefaultItemAnimator()
    }

    fun loadDiaries() {
        mDiariesRepository.getAll(object: DataCallback<List<Diary>>{
            override fun onSuccess(data: List<Diary>) {
                processDiaries(data)
            }

            override fun onError() {
                showError()
            }

        })
    }

    private fun processDiaries(data: List<Diary>) {
        mListAdapter.update(data)
    }

    fun showError() {
        showMessage("error")
    }

    private fun showMessage(s: String) {
        Toast.makeText(mView.requireContext(), s, Toast.LENGTH_SHORT).show()
    }

    fun gotoWriteDiary() {
        showMessage("developing")
    }

}