package com.kpu.item_realm

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        var mConfiguration = RealmConfiguration.Builder()
            .name("BSOMSDB.realm")
            .schemaVersion(1)
            .migration(Migration())
            .build()
        Realm.setDefaultConfiguration(mConfiguration)

        val realm = Realm.getDefaultInstance()
    }
}