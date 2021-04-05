package com.example.mydiary

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mydiary.controller.DiariesController

class DiariesFragment : Fragment() {

    private lateinit var mController: DiariesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mController = DiariesController(this) // 创建，初始化 Controller
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 加载日记界面布局
        val root = inflater.inflate(R.layout.fragment_diaries, container, false)
        //将日记列表控件传入控制器
        mController.setDiariesList(root.findViewById(R.id.diaries_list))
        return root
    }

    override fun onResume() {
        super.onResume()
        mController.loadDiaries() // 加载日志数据
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_write, menu) // 加载菜单的布局文件
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){                                 // 对被点击的 item 的 id 进行判断
            R.id.menu_add -> {                              // 单击"添加"按钮
                mController.gotoWriteDiary()                // 通知控制器，添加新的日记信息
                return true                                 // 返回 true 代表菜单的选择事件已经被处理
            }
        }
        return false                                        // 返回 false 代表菜单的选择事件没有被处理
    }

}