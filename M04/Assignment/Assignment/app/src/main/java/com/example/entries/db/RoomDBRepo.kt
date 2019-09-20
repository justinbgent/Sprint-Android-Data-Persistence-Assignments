package com.example.entries.db

import android.content.Context
import androidx.room.Room
import com.example.entries.model.Book

class RoomDBRepo(private val context: Context): InterfaceDAO {

    override fun updateBookList(): MutableList<Book> {
        return myData.interfaceDAO().updateBookList()
    }

    override fun createBook(book: Book) {
        myData.interfaceDAO().createBook(book)
    }

    override fun updateBook(book: Book) {
        myData.interfaceDAO().updateBook(book)
    }

    override fun deleteBook(book: Book) {
        myData.interfaceDAO().deleteBook(book)
    }

    private val myData by lazy {
        Room.databaseBuilder(
            context.applicationContext,
            BookDB::class.java, "book_database"
        ).fallbackToDestructiveMigration().build()
    }
}