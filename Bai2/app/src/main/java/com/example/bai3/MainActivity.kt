package com.example.bai3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var adapter: StudentAdapter? = null
    private var studentList: ArrayList<Student>? = null // Đảm bảo kiểu ArrayList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách sinh viên
        studentList = ArrayList()
        studentList?.add(Student("Nguyen Van A", "204530"))
        studentList?.add(Student("Tran Thi B", "202943"))
        studentList?.add(Student("Le Van C", "204564"))

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = studentList?.let { StudentAdapter(it) }
        recyclerView?.adapter = adapter

        // Thiết lập ô tìm kiếm
        val searchBar = findViewById<EditText>(R.id.search_bar)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                val keyword = charSequence.toString()
                if (keyword.length > 2) {
                    adapter?.filter(keyword)
                } else {
                    adapter?.filter("") // Hiển thị toàn bộ danh sách khi từ khóa <= 2 ký tự
                }
            }
            override fun afterTextChanged(editable: Editable) {}
        })

    }
}


