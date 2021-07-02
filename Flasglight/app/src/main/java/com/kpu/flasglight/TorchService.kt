package com.kpu.flasglight

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
class TorchService: Service() {


    private var isRunning=false

    private val torch:Torch by lazy{
        Torch(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            "on"->{
                torch.flashOn()
                isRunning=true
                Log.i("switch","on")

            }

            "off"->{
                torch.flashOff()
                isRunning=false
                Log.i("switch","off")
            }

            else->{
                isRunning=!isRunning
                if(isRunning){
                    torch.flashOn()
                    Log.i("switch","on")
                }
                else{
                    torch.flashOff()
                    Log.i("switch","off")

                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

}