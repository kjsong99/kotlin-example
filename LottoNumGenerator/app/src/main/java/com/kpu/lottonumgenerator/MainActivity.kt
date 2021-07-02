package com.kpu.lottonumgenerator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.kpu.lottonumgenerator.LottoNumberMaker.getShuffleLottoNumbers
import kotlinx.android.synthetic.main.activity_constellation.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_name.*
import org.jetbrains.anko.startActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private fun loadDay() :ArrayList<Int>?{
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val month=pref.getInt("month",0)
        val day=pref.getInt("day",0)
        val year=pref.getInt("year",0)

        if (month!=0&&day!=0&&year!=0) {
            val list= arrayListOf<Int>(year,month,day)
            return list
        }
        return null
    }

    private fun loadName() : String? {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        val name = pref.getString("name", "")
        if (name != "") {
            return name!!
        }
        return null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomCard.setOnClickListener {
            startActivity<ResultActivity>(
                "result" to ArrayList(getShuffleLottoNumbers())
            )
        }

        constellationCard.setOnClickListener {

            val arr= loadDay()

            var year=0
            var month=0
            var day=0
            if(arr!=null){
                year=arr[0]
                month=arr[1]
                day=arr[1]
            }


            startActivity<ConstellationActivity>(
                "year" to year,
                "month" to month,
                "day" to day
            )
        }

        nameCard.setOnClickListener {
            startActivity<NameActivity>()
        }
    }
}