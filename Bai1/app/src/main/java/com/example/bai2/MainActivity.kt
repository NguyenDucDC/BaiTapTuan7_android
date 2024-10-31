package com.example.bai2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroupType: RadioGroup
    private lateinit var radioEven: RadioButton
    private lateinit var radioOdd: RadioButton
    private lateinit var radioPerfectSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var listViewNumbers: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các thành phần trong layout
        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroupType = findViewById(R.id.radioGroupType)
        radioEven = findViewById(R.id.radioEven)
        radioOdd = findViewById(R.id.radioOdd)
        radioPerfectSquare = findViewById(R.id.radioPerfectSquare)
        buttonShow = findViewById(R.id.buttonShow)
        listViewNumbers = findViewById(R.id.listViewNumbers)
        textViewError = findViewById(R.id.textViewError)

        // Sự kiện khi nhấn nút Show
        buttonShow.setOnClickListener {
            textViewError.text = ""  // Xóa thông báo lỗi

            // Kiểm tra dữ liệu nhập vào
            val nStr = editTextNumber.text.toString()
            if (nStr.isEmpty()) {
                textViewError.text = "Vui lòng nhập một số nguyên dương."
                return@setOnClickListener
            }

            val n = nStr.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Vui lòng nhập một số nguyên dương hợp lệ."
                return@setOnClickListener
            }

            // Lấy danh sách số theo lựa chọn
            val numbers = when {
                radioEven.isChecked -> getEvenNumbers(n)
                radioOdd.isChecked -> getOddNumbers(n)
                radioPerfectSquare.isChecked -> getPerfectSquareNumbers(n)
                else -> {
                    textViewError.text = "Vui lòng chọn một loại số."
                    return@setOnClickListener
                }
            }

            // Hiển thị danh sách số trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listViewNumbers.adapter = adapter
        }
    }

    // Lấy danh sách số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    // Lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    // Lấy danh sách số chính phương từ 0 đến n
    private fun getPerfectSquareNumbers(n: Int): List<Int> {
        val perfectSquares = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            perfectSquares.add(i * i)
            i++
        }
        return perfectSquares
    }
}
