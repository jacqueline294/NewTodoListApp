package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.Date



data class Todo(
    var id : Int,
    var title : String,
    var createdAt:Date
)


@RequiresApi(Build.VERSION_CODES.O)
fun getFakeTodo(): List<Todo>{
    return listOf<Todo>(
        Todo(1,"First todo", Date.from(Instant.now())),
        Todo(2,"Second todo", Date.from((Instant.now())))

        );

}
