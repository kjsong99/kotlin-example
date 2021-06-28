package com.kpu.calculatortext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private fun saveData(num1:Int,num2:Int){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()

        editor.putInt("KEY_NUM1",num1).apply()
        editor.putInt("KEY_NUM2",num2).apply()
    }

    private fun loadData(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        var num1=pref.getInt("KEY_NUM1",0)
        var num2=pref.getInt("KEY_NUM2",0)

        if(num1!=0&&num2!=0){
            editNum1.setText(num1.toString())
            editNum2.setText(num2.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        plusButton.setOnClickListener {
            startActivity<PlusActivity>(
                "num1" to editNum1.text.toString(),
                "num2" to editNum2.text.toString()
            )
            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())


        }
        minusButton.setOnClickListener {
            startActivity<MinusActivity>(
                "num1" to editNum1.text.toString(),
                "num2" to editNum2.text.toString()
            )
            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())

        }
        divButton.setOnClickListener {
            startActivity<DivActivity>(
                "num1" to editNum1.text.toString(),
                "num2" to editNum2.text.toString()
            )
            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())

        }
        mulButton.setOnClickListener {
            startActivity<MulActivity>(
                "num1" to editNum1.text.toString(),
                "num2" to editNum2.text.toString()
            )
            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt())

        }
    }
}