package com.example.mydiary

import com.example.mydiary.model.Diary

class MockDiaries {
    companion object {
        private val DESCRIPTION = "今天，天气清凉，在第九巷大道上，我遇到一群年轻人，他们优雅的弹奏着手风琴，围观的人大多是少男少女，他们目不转睛"
        fun mock(): MutableMap<String, Diary> {
            return mock()
        }
        fun mock(data: MutableMap<String, Diary>) : Map<String, Diary> {
            val test1 = getDiary("2020-11-02 艺术节")
            data[test1.getId()] = test1
            val test2 = getDiary("2020-11-03 参加会展")
            data[test2.getId()] = test2
            val test3 = getDiary("2020-11-04 今天心情糟糕")
            data[test3.getId()] = test3
            val test4 = getDiary("2020-11-05 学习了新的架构")
            data[test4.getId()] = test4
            val test5 = getDiary("2020-11-06 持续进步")
            data[test5.getId()] = test5
            val test6 = getDiary("2020-11-07 我还在成长")
            data[test6.getId()] = test6
            val test7 = getDiary("2020-11-08 合作")
            data[test7.getId()] = test7
            val test8 = getDiary("2020-11-09 进步")
            data[test8.getId()] = test8
            return data
        }

        private fun getDiary(title: String): Diary {
            return Diary(title, DESCRIPTION)
        }
    }
}