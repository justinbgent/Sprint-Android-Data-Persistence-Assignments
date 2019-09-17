package com.example.entries

import com.example.entries.activity.MainActivity
import com.example.entries.model.Book

object SharedPrefsDao {

    const val ID_LIST = "ID_LIST"

    fun saveAllIds() {
        var ids = ""
        for (i in MainActivity.bookList.indices) {
            ids +=
                if (MainActivity.bookList.size - 1 != i) {
                    "$i,"
                } else {
                    "$i"
                }
        }
        MainActivity.preferences.edit()
            .putString(ID_LIST, ids)
            .apply()
    }

    fun saveAllBookCvs(){
        for (i in MainActivity.bookList.indices) {
            MainActivity.preferences.edit()
                .putString(MainActivity.bookList[i].id, MainActivity.bookList[i].toCsvString())
                .apply()
        }
    }


    fun getAllBookIds(): String {
        return MainActivity.preferences.getString(ID_LIST, "") ?: ""
    }


    fun getBookCSV(id: String): String {
        return MainActivity.preferences.getString(id, "") ?: ""
    }

    fun updateBook(book: Book) {
        var bookUpdated = false
        for (i in MainActivity.bookList.indices) {
            if (MainActivity.bookList[i].id == book.id) {
                MainActivity.bookList[i] = book
                bookUpdated = true
            }
        }
        if (!bookUpdated) {
            MainActivity.bookList.add(book)
        }
    }
}