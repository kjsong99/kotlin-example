package com.kpu.calculatortest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {
    private fun saveData(num1:Int,num2:Int,result:Float){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()

        editor.putInt("KEY_NUM1",num1).apply()
        editor.putInt("KEY_NUM2",num2).apply()
        editor.putFloat("KEY_RESULT",result).apply()
    }

    private fun loadData(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        var num1=pref.getInt("KEY_NUM1",0)
        var num2=pref.getInt("KEY_NUM2",0)
        var result=pref.getFloat("KEY_RESULT",0f)

        if(num1!=0&&num2!=0&&result!=0f){
            editNum1.setText(num1.toString())
            editNum2.setText(num2.toString())
            resultText.setText(result.toString())
        }
    }

    private fun deleteNum1(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.remove("KEY_NUM1")
        editNum1.setText(null)
    }

    private fun deleteNum2(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.remove("KEY_NUM2")
        editNum2.setText(null)
    }

    private fun deleteAll(){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()
        editor.clear()
        editNum1.setText(null)
        editNum2.setText(null)
        resultText.setText("계산 결과 :")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title="CalculatorTest"

        var btnArray=arrayOf<Button>(btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9)

        editNum1.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                for(i in 0..9){
                    btnArray[i].setOnClickListener {
                        editNum1.setText(editNum1.text.toString()+i.toString())
                    }
                }
            }
        }

        editNum2.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                for(i in 0..9){
                    btnArray[i].setOnClickListener {
                        editNum2.setText(editNum2.text.toString()+i.toString())
                    }
                }
            }
        }

        btnNum1.setOnClickListener {
            deleteNum1()
        }

        btnNum2.setOnClickListener {
            deleteNum2()
        }

        btnAll.setOnClickListener {
            deleteAll()
        }

        loadData()
        plusButton.setOnClickListener {

            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                toast("숫자를 입력하세요!")
                return@setOnClickListener

            }

            val resultVal=editNum1.text.toString().toFloat()+editNum2.text.toString().toFloat()
            resultText.setText("계산결과 : "+resultVal.toString())
            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt(),resultVal)


        }
        minusButton.setOnClickListener {

            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                toast("숫자를 입력하세요!")
                return@setOnClickListener

            }

            val resultVal=editNum1.text.toString().toFloat()-editNum2.text.toString().toFloat()
            resultText.setText("계산결과 : "+resultVal.toString())
            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt(),resultVal)


        }
        divButton.setOnClickListener {
            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                toast("숫자를 입력하세요!")
                return@setOnClickListener

            }
            if(editNum2.text.toString().toInt()==0){
                toast("0으로 나눌 수 없습니다!")
                return@setOnClickListener

            }
            val resultVal=editNum1.text.toString().toFloat()/editNum2.text.toString().toFloat()
            resultText.setText("계산결과 : "+resultVal.toString())

            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt(),resultVal)

        }
        mulButton.setOnClickListener {

            if(editNum1.text.toString()==""||editNum2.text.toString()==""){
                toast("숫자를 입력하세요!")
                return@setOnClickListener

            }

            val resultVal=editNum1.text.toString().toFloat()*editNum2.text.toString().toFloat()
            resultText.setText("계산결과 : "+resultVal.toString())

            saveData(editNum1.text.toString().toInt(),editNum2.text.toString().toInt(),resultVal)


        }
    }
}