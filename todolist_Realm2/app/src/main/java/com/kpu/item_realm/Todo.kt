package com.kpu.todolist_realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
open class Todo(
    @PrimaryKey var id: Long = 0,
    var title: String = "",
    var date: Long = 0,
    var address:String="",
    var number:Int=0
) : RealmObject() { }