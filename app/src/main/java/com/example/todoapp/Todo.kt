package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date
import java.util.Date.from


data class Todo(
    var id : Int,
    var title : String,
    var createdAt:Date
)


fun getFakeTodo(): List<Todo>{
    return listOf<Todo>(
        Todo(1,"First todo", from(Instant.now()))
    )

}
