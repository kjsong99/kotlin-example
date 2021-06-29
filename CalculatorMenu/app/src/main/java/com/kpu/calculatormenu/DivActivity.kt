package com.kpu.calculatormenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_div.*

class DivActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_div)

        var num1=intent.getStringExtra("num1")!!.toInt()
        var num2=intent.getStringExtra("num2")!!.toInt()

        val divResult=num1/num2
        resultText2.setText(divResult.toString())
    }
}