package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import java.time.Instant


class TodoViewModel : ViewModel(){
    val todoDao= MainApplication.todoDatabase.getTodoDao()

    val todoList: LiveData<List<Todo>> = todoDao.getAllTodo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title:String){
        todoDao.addTodo(Todo(title = title, createdAt = java.util.Date.from(Instant.now())))
    }

    fun updateTodo(id: Int, title: String){


    }

    fun deleteTodo(id: Int){
        todoDao.deleteTodo(id)

    }

}