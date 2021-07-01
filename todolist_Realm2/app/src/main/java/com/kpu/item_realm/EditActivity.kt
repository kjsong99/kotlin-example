package com.kpu.todolist_realm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.util.*

class EditActivity : AppCompatActivity() {
    val calendar:Calendar= Calendar.getInstance()
    val realm= Realm.getDefaultInstance()
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        }
        val id=intent.getLongExtra("id",-1L)

        if(id==-1L)
        {
           insertMode()
        }
        else{
            updateMode(id)
        }

    }

    private fun insertMode(){
        deleteFab.visibility= View.GONE
        doneFab.setOnClickListener {
            insertTodo()
        }
    }

    private fun updateMode(id:Long){
        val todo=realm.where<Todo>().equalTo("id",id).findFirst()!!
        editText1.setText(todo.title)
        editText2.setText(todo.number.toString())
        editText3.setText(todo.address)
        calendarView.date=todo.date

        doneFab.setOnClickListener {
            updateTodo(id)
        }

        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }

    private fun insertTodo(){
        realm.beginTransaction()
        val todo=realm.createObject<Todo>(nextId())
        todo.title=editText1.text.toString()
        todo.date=calendar.timeInMillis
        todo.number=editText2.text.toString().toInt()
        todo.address=editText3.text.toString()
        realm.commitTransaction()

        toast("추가됨")

        alert("내용이 추가되었습니다"){
            yesButton {
                finish()
            }
        }.show()

    }

    private fun updateTodo(id:Long){
        realm.beginTransaction()
        val todo=realm.where<Todo>().equalTo("id",id).findFirst()!!

        todo.title=editText1.text.toString()
        todo.date=calendar.timeInMillis
        todo.number=editText2.text.toString().toInt()
        todo.address=editText3.text.toString()

        realm.commitTransaction()

        toast("변경됨")

        alert("내용이 변경되었습니다")
        {
            yesButton { finish() }
        }.show()
    }

    private fun deleteTodo(id:Long){
        realm.beginTransaction()
        val todo=realm.where<Todo>().equalTo("id",id).findFirst()!!
        todo.deleteFromRealm()
        realm.commitTransaction()

        toast("삭제됨")

        alert("내용이 삭제되었습니다")
        {
            yesButton { finish() }
        }.show()
    }

    private fun nextId():Int{
        val maxId=realm.where<Todo>().max("id")
        if(maxId!=null)
        {
            return maxId.toInt()+1
        }

        return 0
    }
}