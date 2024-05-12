package com.example.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoapp.Todo


@Database(entities = [Todo::class], version = 1)
 abstract class TodoDatabase: RoomDatabase() {
     companion object{
         const val NAME = "Todo_DB"
     }
    abstract fun getTodoDao(): TodoDao

}