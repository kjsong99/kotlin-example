package com.kpu.sensorlist


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() ,SensorEventListener{
    private val sensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }


    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val sensors: List<Sensor> = sensorManager.getSensorList(Sensor.TYPE_ALL)
        var sensorAdapter= SensorListAdapter(this,sensors)

        listView.adapter=sensorAdapter

        var size:Int=0
        count.setText("센서 개수 : "+listView.count.toString())






    }

    override fun onSensorChanged(event: SensorEvent?) {
        TODO("Not yet implemented")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        TODO("Not yet implemented")
    }
}
