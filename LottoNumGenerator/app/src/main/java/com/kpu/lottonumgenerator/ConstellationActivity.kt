package com.kpu.lottonumgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.CalendarView
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_constellation.*
import kotlinx.android.synthetic.main.activity_name.*
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class ConstellationActivity : AppCompatActivity() {

    private fun saveData(year:Int,month:Int,day:Int){
        val pref= PreferenceManager.getDefaultSharedPreferences(this)
        val editor=pref.edit()

        editor.putInt("year",year).apply()
        editor.putInt("month",month).apply()
        editor.putInt("day",day).apply()

//        editor.putInt("KEY_HEIGHT",height).apply()
    }

    private fun loadData() {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val month=pref.getInt("month",0)
        val day=pref.getInt("day",0)
        val year=pref.getInt("year",0)
        if (month!=0&&day!=0&&year!=0) {
            datePicker.updateDate(year,month,day)

        }
    }

    fun makeConstellationString(month:Int,day:Int):String{
        val target="${month+1}${String.format("%02d",day)}".toInt()

        when(target){
            in 101..119->{
                return "염소자리"
            }

            in 120..218->{
                return "물병자리"
            }

            in 219..320->{
                return "물고기자리"
            }

            in 321..419->{
                return "양자리"
            }

            in 420..520->{
                return "황소자리"
            }

            in 521..621->{
                return "쌍둥이자리"
            }

            in 622..722->{
                return "게자리"
            }

            in 723..822->{
                return "사자자리"
            }

            in 823..923->{
                return "처녀자리"
            }

            in 924..1022->{
                return "천칭자리"
            }

            in 1023..1122->{
                return "전갈자리"
            }

            in 1123..1224->{
                return "사수자리"
            }

            in 1225..1231->{
                return "염소자리"
            }

            else->{
                return "기타별자리"
            }


        }
    }

    fun getLottoNumbersFromHash(str:String):MutableList<Int>{

        val targetString= SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(Date())+str
        val list= mutableListOf<Int>()

        for (number in 1..45)
        {
            list.add(number)
        }

        list.shuffle(Random(targetString.hashCode().toLong()))

        return list.subList(0,6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constellation)


        button.setOnClickListener {
            datePicker.updateDate(datePicker.year,monthEditText.text.toString().toInt()-1,dayEditText.text.toString().toInt())
        }






        goResultButton.setOnClickListener {

            saveData(datePicker.year,datePicker.month,datePicker.dayOfMonth)
            startActivity<ResultActivity>(
                "result" to ArrayList(getLottoNumbersFromHash(makeConstellationString(datePicker.month,datePicker.dayOfMonth))),
                "constellation" to makeConstellationString(datePicker.month,datePicker.dayOfMonth)
            )
        }

        textView.setText(makeConstellationString(datePicker.month,datePicker.dayOfMonth))

        val calendar=Calendar.getInstance()
        datePicker.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)
        ,object :CalendarView.OnDateChangeListener,
        DatePicker.OnDateChangedListener{
                override fun onDateChanged(
                    view: DatePicker?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    textView.text=makeConstellationString(datePicker.month,datePicker.dayOfMonth)
                    monthEditText.setText((datePicker.month+1).toString())
                    dayEditText.setText(datePicker.dayOfMonth.toString())

                }

                override fun onSelectedDayChange(
                    view: CalendarView,
                    year: Int,
                    month: Int,
                    dayOfMonth: Int
                ) {
                    TODO("Not yet implemented")
                }
        })

        loadData()

    }
}