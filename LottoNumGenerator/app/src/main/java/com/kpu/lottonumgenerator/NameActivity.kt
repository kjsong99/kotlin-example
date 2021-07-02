package com.kpu.lottonumgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.widget.Toast
import com.kpu.lottonumgenerator.LottoNumberMaker.getLottoNumbersFromHash
import kotlinx.android.synthetic.main.activity_name.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class NameActivity : AppCompatActivity() {

    private fun saveData(name:String){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()

        editor.putString("name",name).apply()

//        editor.putInt("KEY_HEIGHT",height).apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val name = pref.getString("name", "")
        if (name != "") {
            editText1.setText(name)
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        loadData()

        goButton.setOnClickListener {
            if(TextUtils.isEmpty(editText1.text.toString())){
                toast("이름을 입력하세요")
                return@setOnClickListener
                }

            saveData(editText1.text.toString())


            startActivity<ResultActivity>(
                "result" to ArrayList(getLottoNumbersFromHash(editText1.text.toString())),
                "name" to editText1.text.toString()
            )
        }
    }
}