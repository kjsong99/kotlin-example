package com.kpu.todolist_realm

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.kpu.todolist_realm.databinding.ActivityMainBinding
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    val realm= Realm.getDefaultInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val realmResult=realm.where<Todo>().findAll().sort("date", Sort.DESCENDING)



        val adapter=TodoListAdapter(realmResult)

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
