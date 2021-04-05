package com.example.mydiary.model

class DiariesRepository : DataSource<Diary> {

    companion object {
        @Volatile private var mInstance: DiariesRepository ? = null
        fun getInstance(): DiariesRepository {
            if (mInstance == null) {
                mInstance = DiariesRepository()
            }
            return mInstance!!
        }
    }

    private var mLocalDataSource: DataSource<Diary> = DiariesLocalDataSource.get()!!
    private var mMemoryCache: MutableMap<String, Diary> = mutableMapOf()

    // 获取所有日记数据
    override fun getAll(callback: DataCallback<List<Diary>>) {
        if (!mMemoryCache.isNullOrEmpty()) {
            callback.onSuccess(ArrayList(mMemoryCache.values))
            return
        }
        mLocalDataSource.getAll(object : DataCallback<List<Diary>>{
            override fun onSuccess(data: List<Diary>) {
                updateMemoryCache(data)
                val memoryCacheValue = ArrayList(mMemoryCache.values)
                callback.onSuccess(memoryCacheValue)
            }

            override fun onError() {
                callback.onError()
            }

        })
        //TODO
        mLocalDataSource.getAll(callback)
    }

    // 获取某个日记数据
    override fun get(id: String, callback: DataCallback<Diary>) {
        var cachedDiary = getDiaryByIdFromMemory(id)
        if (cachedDiary != null) {
            callback.onSuccess(cachedDiary)
            return
        }
        mLocalDataSource.get(id, object : DataCallback<Diary>{
            override fun onSuccess(data: Diary) {
                mMemoryCache[data.getId()] = data
                callback.onSuccess(data)
            }

            override fun onError() {
                callback.onError()
            }
        })
    }

    private fun getDiaryByIdFromMemory(id: String): Diary? {
        return if (mMemoryCache.isNullOrEmpty()) {
            null
        } else {
            mMemoryCache[id]
        }
    }

    // 更新日记信息
    override fun update(diary: Diary) {
        mLocalDataSource.update(diary)
        mMemoryCache[diary.getId()] = diary
    }

    // 清空日记数据
    override fun clear() {
        mLocalDataSource.clear()
        mMemoryCache.clear()
    }

    // 删除某日记数据
    override fun delete(id: String) {
        mLocalDataSource.delete(id)
        mMemoryCache.remove(id)
    }


    private fun updateMemoryCache(diaryList: List<Diary>) {
        mMemoryCache.clear()
        for (diary in diaryList) {
            mMemoryCache[diary.getId()] = diary
        }
    }
}