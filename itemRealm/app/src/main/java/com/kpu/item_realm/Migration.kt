package com.kpu.item_realm

import android.os.Parcel
import android.os.Parcelable
import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration

open class Migration() : RealmMigration, Parcelable {

    constructor(parcel: Parcel) : this() {
    }

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {

        var oldVersion = oldVersion
        val schema = realm.schema
        if (oldVersion == 0L) {
            val tdmSchema = schema.get("TransferDetailModel")

            tdmSchema!!
                .addField("MainUniqueId", String::class.java, FieldAttribute.REQUIRED)
                .transform { obj -> obj.set("MainUniqueId", obj.getString("DetailUniqueId"))}
                .removeField("DetailUniqueId")
            oldVersion++
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Migration> {
        override fun createFromParcel(parcel: Parcel): Migration {
            return Migration(parcel)
        }

        override fun newArray(size: Int): Array<Migration?> {
            return arrayOfNulls(size)
        }
    }
}