package com.example.entries.Controller

import android.content.Intent
import com.example.entries.model.Book
import com.example.entries.model.Constants

object BooksController {
// unneeded with recycler view

    fun handleEditActivityResult(intent: Intent) {
        val bookCSV = intent.getStringExtra(Constants.STRING_KEY)
        if (bookCSV != null) {
            val book = Book(bookCSV)
            val index = book.id
            Constants.bookList[index!!.toInt()] = book
        }
    }

    fun handleAddActivityResult(intent: Intent) {
        val bookCSV = intent.getStringExtra(Constants.STRING_KEY)
        if (bookCSV != null) {
            val book = Book(bookCSV)
            Constants.bookList.add(book)
        }
    }
}