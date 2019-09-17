package com.example.entries

import com.example.entries.model.Book
import com.example.entries.model.BooksModel

class SharedPrefsDao {

    companion object{
        const val ID_LIST = "ID_LIST"
    }


    fun saveAllIds() {
        var ids = ""
        for (i in BooksModel.bookList.indices) {
            ids +=
                if (BooksModel.bookList.size - 1 != i) {
                    "$i,"
                } else {
                    "$i"
                }
        }
        SharedPrefOperations.preferences.edit()
            .putString(ID_LIST, ids)
            .apply()
    }

    fun saveAllBookCvs(){
        for (i in BooksModel.bookList.indices) {
            SharedPrefOperations.preferences.edit()
                .putString(BooksModel.bookList[i].id, BooksModel.bookList[i].toCsvString())
                .apply()
        }
    }


    fun getAllBookIds(): String {
        return SharedPrefOperations.preferences.getString(ID_LIST, "") ?: ""
    }


    fun getBookCSV(id: String): String {
        return SharedPrefOperations.preferences.getString(id, "") ?: ""
    }

    fun updateBook(book: Book) {
        var bookUpdated = false
        for (i in BooksModel.bookList.indices) {
            if (BooksModel.bookList[i].id == book.id) {
                BooksModel.bookList[i] = book
                bookUpdated = true
            }
        }
        if (!bookUpdated) {
            BooksModel.bookList.add(book)
        }
    }
}