package com.kpu.lottonumgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_result.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {
    val lottoImageStartId=R.drawable.ball_01
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result=intent.getIntegerArrayListExtra("result")
        val name=intent.getStringExtra("name")
        val constellation=intent.getStringExtra("constellation")
        resultLabel.setText("랜덤으로 생성된 로또 번호 입니다.")

        moneyImage.setOnClickListener {
            finish()
        }

        if(!TextUtils.isEmpty(name))
        {
            resultLabel.text="${name}님의 ${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())} 로또 번호 입니다."
        }

        if(!TextUtils.isEmpty(constellation))
        {
            resultLabel.text="${constellation}의 ${SimpleDateFormat("yyyy년 MM월 dd일").format(Date())} 로또 번호 입니다."
        }

        result?.let{
            updateLottoBallImage(result.sortedBy { it })
        }


    }

    fun updateLottoBallImage(result:List<Int>){
        if(result.size<6){
            return
        }
        ballImage1.setImageResource(lottoImageStartId+(result[0]-1))
        ballImage2.setImageResource(lottoImageStartId+(result[1]-1))
        ballImage3.setImageResource(lottoImageStartId+(result[2]-1))
        ballImage4.setImageResource(lottoImageStartId+(result[3]-1))
        ballImage5.setImageResource(lottoImageStartId+(result[4]-1))
        ballImage6.setImageResource(lottoImageStartId+(result[5]-1))
    }


}