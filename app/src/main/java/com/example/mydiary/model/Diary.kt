package com.example.mydiary.model

import androidx.annotation.Nullable
import androidx.fragment.app.FragmentActivity
import java.util.*

// Model 层构建，尽量做到不存在业务逻辑，也不会持有 View 和 Controller,支持在多种页面进行复用与流转
class Diary {

    private var id: String
    private var title: String?
    private var description: String?

    constructor(
        title: String?,
        description: String?,
    ) : this(title, description, UUID.randomUUID().toString())

    constructor(
        title: String?,
        description: String?,
        id: String
    ) {
        this.id = id
        this.title = title
        this.description = description
    }

    fun getId(): String {
        return id
    }

    fun getTitle(): String? {
        return title
    }

    fun getDescription(): String? {
        return description
    }

    fun setTitle(title: String?) {
        this.title = title
    }

    fun setDescription(description: String?) {
        this.description = description
    }
}