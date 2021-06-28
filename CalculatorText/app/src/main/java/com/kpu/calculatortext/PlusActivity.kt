package com.kpu.calculatortext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_minus.*
import kotlinx.android.synthetic.main.activity_minus.resultText
import kotlinx.android.synthetic.main.activity_plus.*

class PlusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plus)

        var num1=intent.getStringExtra("num1")!!.toInt()
        var num2=intent.getStringExtra("num2")!!.toInt()

        val plusResult=num1+num2
        resultText3.setText(plusResult.toString())
    }
}