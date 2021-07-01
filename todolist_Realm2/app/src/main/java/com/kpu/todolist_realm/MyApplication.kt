package com.kpu.todolist_realm

import android.app.Application
import io.realm.Realm

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}