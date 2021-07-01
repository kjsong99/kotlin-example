package com.kpu.sensorlist

import android.content.Context
import android.hardware.Sensor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class SensorListAdapter(val context: Context, val sensorList:List<Sensor>) :BaseAdapter(){
    override fun getCount(): Int {
        return sensorList.size
    }

    override fun getItem(position: Int): Any {
        return sensorList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view:View=LayoutInflater.from(context).inflate(R.layout.sensor_item,null)

        val name=view.findViewById<TextView>(R.id.textView)
        val power=view.findViewById<TextView>(R.id.textView2)
        val res=view.findViewById<TextView>(R.id.textView3)
        val range=view.findViewById<TextView>(R.id.textView4)

        var sensor=sensorList[position]

        name.text=sensor.name
        power.text=sensor.power.toString()
        res.text=sensor.resolution.toString()

        range.text=sensor.maximumRange.toString()


        return view
    }

}