package com.example.todoapp

import android.annotation.SuppressLint
import java.time.Instant
import java.util.Date

object TodoManager {
    private val todoList = mutableListOf<Todo>()

    fun getAllTodo(): List<Todo>{
        return todoList
    }

    @SuppressLint("NewApi")
    fun addTodo(title:String){
        todoList.add(Todo(System.currentTimeMillis().toInt(), title, Date.from(Instant.now())))

    }

    fun updateTodo(id:Int, title:String){
        todoList.find { it.id == id }?.let { todo ->
            todo.title = title
        }
    }

    fun deleteTodo(id: Int){
        todoList.removeIf{ it.id == id }

    }
}