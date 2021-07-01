package com.kpu.todolist_realm

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration


class Migration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        var oldVersion = oldVersion
        val schema = realm.schema
        if (oldVersion == 0L) {
            val mNoteSchema = schema["NoteModel"]
            //추가된 필드 addField 메서드로 지정함
            mNoteSchema!!.addField("menuPknumber", Int::class.java, FieldAttribute.REQUIRED)
                .transform { obj -> obj["menuPknumber"] = 0 }
            oldVersion++
        }
    }

    companion object {
        private const val TAG = "simplenote"
    }
}