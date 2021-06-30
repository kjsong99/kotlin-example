package com.kpu.paintbrush

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View

class MainActivity() :AppCompatActivity(){
    companion object{
        const val CIRCLE=1
        const val RECT=2
        const val LINE=3

        const val RED=4
        const val GREEN=5
        const val BLUE=6
        var curShape=LINE
        var color = Color.RED
        var curColor:Int=4
        var mode = Paint.Style.STROKE

        var cX:Float=40f
        var cY:Float=40f




    }





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_line->{
                curShape=LINE

                return true
            }

            R.id.action_circle->{
                curShape= CIRCLE

            }

            R.id.action_rectangle->{
                curShape= RECT
            }

            R.id.action_green->{
                curColor=GREEN
            }

            R.id.action_blue->{
                curColor= BLUE
            }

            R.id.action_red->{
                curColor= RED
            }
        }
        return false
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(MyPaintView(this))

    }

    private class MyPaintView (context: Context):View(context){


        override fun onDraw(canvas: Canvas?) {
            val paint= Paint()
            paint.isAntiAlias = true
            paint.strokeWidth = 5f
            paint.style = Paint.Style.STROKE
            paint.color=color

            when(curColor){
                RED->{
                    curColor=RED
                    paint.color= Color.RED
                    invalidate()
                }

                GREEN->{
                    curColor=GREEN
                    paint.color=Color.GREEN
                    invalidate()
                }

                BLUE->{
                    curColor=BLUE
                    paint.color= Color.BLUE
                    invalidate()
                }

            }



            when(curShape){
                CIRCLE ->{
                    canvas!!.drawCircle(100f,100f,100f,paint)
                    invalidate()
                }
                RECT ->{
                    canvas!!.drawRect(100f,100f,200f,200f,paint)
                    invalidate()
                }
                LINE->{
                    canvas!!.drawLine(100f,100f,200f,200f,paint)
                    invalidate()
                }
            }



            }
        }
}