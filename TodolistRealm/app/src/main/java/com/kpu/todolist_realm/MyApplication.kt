package com.kpu.todolist_realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this) //초기화

        val realmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded() //개발중
            .schemaVersion(1) //noteModel 스키가 upgrade 0 => 1
            .migration( Migration())
            .name("todolist.realm")
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}