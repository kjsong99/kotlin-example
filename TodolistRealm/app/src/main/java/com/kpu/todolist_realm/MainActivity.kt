package com.kpu.todolist_realm

import android.app.Application
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import io.realm.*

import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {


    val realm = Realm.getDefaultInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val realmResult=realm.where<Todo>().findAll().sort("date", Sort.DESCENDING)



        val adapter= TodoListAdapter(realmResult)

        listView.adapter=adapter

        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }


        listView.setOnItemClickListener { parent, view, position, id ->
            startActivity<EditActivity>("id" to id)
        }



        fabAdd.setOnClickListener {
            startActivity<EditActivity>()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
