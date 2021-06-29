package com.kpu.calculatortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_minus.*

class MinusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_minus)

        var num1=intent.getStringExtra("num1")!!.toInt()
        var num2=intent.getStringExtra("num2")!!.toInt()

        val minusResult=num1-num2
        resultText.setText(minusResult.toString())
    }
}