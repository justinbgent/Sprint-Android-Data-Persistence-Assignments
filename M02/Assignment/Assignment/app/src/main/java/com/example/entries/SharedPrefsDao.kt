package com.example.entries

import com.example.entries.model.Book
import com.example.entries.model.Constants

class SharedPrefsDao {

    companion object{
        const val ID_LIST = "ID_LIST"
    }


    fun saveAllIds() {
        var ids = ""
        for (i in Constants.bookList.indices) {
            ids +=
                if (Constants.bookList.size - 1 != i) {
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
        for (i in Constants.bookList.indices) {
            SharedPrefOperations.preferences.edit()
                .putString(Constants.bookList[i].id, Constants.bookList[i].toCsvString())
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
        for (i in Constants.bookList.indices) {
            if (Constants.bookList[i].id == book.id) {
                Constants.bookList[i] = book
                bookUpdated = true
            }
        }
        if (!bookUpdated) {
            Constants.bookList.add(book)
        }
    }
}