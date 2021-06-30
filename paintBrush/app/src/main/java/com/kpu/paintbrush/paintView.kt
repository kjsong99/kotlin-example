package com.kpu.paintbrush

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class paintView(context:Context, attrs: AttributeSet?) :
    View(context, attrs){

    var imagePath : String? = null

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        try {
            if (imagePath != null){
                var bitmap = BitmapFactory.decodeFile(imagePath)
                canvas.scale(2f,2f,0f,0f)
                canvas.drawBitmap(bitmap!!,0f,0f,null)
                bitmap.recycle()
            }
        }catch (e : Exception){}
    }
}