package com.kpu.tiltsensorrect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.hardware.SensorEvent
import android.media.AudioManager
import android.media.ToneGenerator
import android.view.View

class TiltView(context: Context?) : View(context) {
    private val greenPaint: Paint =Paint()
    private val blackPaint:Paint=Paint()

    val toneGen1 = ToneGenerator(AudioManager.STREAM_MUSIC, 100)

    var yCoord:Float=0f
    var xCoord:Float=0f

    private var cX:Float=0f
    private var cY:Float=0f

    init{
        greenPaint.color= Color.GREEN
        blackPaint.style=Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawRect(cX,cY,cX+100f,cY+100f,blackPaint)
        canvas?.drawRect(cX+xCoord,cY+yCoord,cX+xCoord+100f,cY+yCoord+100f,greenPaint)


        canvas?.drawLine(cX+20,cY+50,cX+80,cY+50,blackPaint)
        canvas?.drawLine(cX+50,cY+20,cX+50,cY+80,blackPaint)
    }

    fun onSensorEvent(event: SensorEvent) {
        yCoord = event.values[0] * 20
        xCoord = event.values[1] * 20
        if (Math.abs(yCoord) > 50f || Math.abs(xCoord) > 50f) {
            toneGen1.startTone(ToneGenerator.TONE_CDMA_ABBR_ALERT, 300)
            }
            invalidate()
        }
        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            cX = w / 2f
            cY = h / 2f
        }

}