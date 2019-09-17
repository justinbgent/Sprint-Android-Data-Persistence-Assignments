package com.example.entries

import com.example.entries.model.Book

interface BookRepoInterface {
    fun saveAllBookCvs()
    fun saveAllIds()
    fun getAllBookIds(): String
    fun getBookCSV(id: String): String
    fun updateBook(book: Book)
}