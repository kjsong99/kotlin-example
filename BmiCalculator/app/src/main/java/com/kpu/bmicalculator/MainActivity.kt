package com.kpu.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private fun saveData(height:Int,weight:Int){
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()

        editor.putInt("KEY_HEIGHT",height).apply()
    }

    private fun loadData(){
        val pref=PreferenceManager.getDefaultSharedPreferences(this)
        val height=pref.getInt("KEY_HEIGHT",0)
        val weight=pref.getInt("KEY_WEIGHT",0)

        if(height!=0&&weight!=0){
            heightText.setText(height.toString())
            weightText.setText(weight.toString())
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()

        resultButton.setOnClickListener {
            saveData(heightText.text.toString().toInt(),
            weightText.text.toString().toInt())
            startActivity<ResultActivity>(
                "weight" to weightText.text.toString(),
                "height" to heightText.text.toString()
            )
        }
    }
}