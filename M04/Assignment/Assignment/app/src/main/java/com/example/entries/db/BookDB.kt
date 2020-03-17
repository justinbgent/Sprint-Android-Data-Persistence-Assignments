package com.example.entries.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entries.model.Book

@Database(entities = [Book::class], version = 2, exportSchema = false)
abstract class BookDB: RoomDatabase() {
    abstract fun interfaceDAO(): InterfaceDAO
}