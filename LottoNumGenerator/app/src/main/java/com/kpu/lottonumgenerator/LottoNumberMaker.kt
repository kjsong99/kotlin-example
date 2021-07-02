package com.kpu.lottonumgenerator

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

object LottoNumberMaker {

    fun getRandomLottoNumber():Int{
        return Random.nextInt(45)+1
    }


    fun getLottoNumbersFromHash(name:String):MutableList<Int>{

        val targetString= SimpleDateFormat("yyyy-MM-dd-HH,mm", Locale.KOREA).format(Date())+name
        val list= mutableListOf<Int>()

        for (number in 1..45)
        {
            list.add(number)
        }

        list.shuffle(Random(targetString.hashCode().toLong()))

        return list.subList(0,6)
    }


    fun getShuffleLottoNumbers():MutableList<Int>{
        val lottoNumbers= mutableListOf<Int>()

        for(number in 1..45){
            lottoNumbers.add(number)
        }

        lottoNumbers.shuffle()

        return lottoNumbers.subList(0,6)

    }


}