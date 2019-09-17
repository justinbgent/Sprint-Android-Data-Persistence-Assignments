package com.example.entries

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.entries.model.Book
import com.example.entries.model.BooksModel

class SharedPrefOperations(private val context: Context): BookRepoInterface {
    companion object {
//        const val STRING_KEY = "STRING_KEY"
//        const val ID_KEY = "ID_KEY"
//        const val ADD_BOOK = 345
//        const val EDIT_BOOK = 545
//        const val USER_PREFERENCES = "USER_PREFERENCES"
//
//
//
        lateinit var preferences: SharedPreferences
    }

    private val sharedPrefsDao = SharedPrefsDao()

    fun updateBookList()/*: MutableList<Book>*/ {
        val idList = sharedPrefsDao.getAllBookIds()
        val oldList = idList.split(",")
        val ids = ArrayList<String>(oldList.size)
        if (idList.isNotBlank()) {
            ids.addAll(oldList)
        }
        ids.forEach {
            Log.i("pleasework", it)
            val csvString = sharedPrefsDao.getBookCSV(it)
            sharedPrefsDao.updateBook(Book(csvString))
        }
    }

    fun initiatePreferences() {
        preferences = context.getSharedPreferences(
            BooksModel.USER_PREFERENCES, Context.MODE_PRIVATE
        )
    }

    fun saveUserPreferences(){
        sharedPrefsDao.saveAllBookCvs()
        sharedPrefsDao.saveAllIds()
    }

    override fun saveAllBookCvs() {
    }

    override fun saveAllIds() {
    }

    override fun getAllBookIds(): String {
        return ""
    }

    override fun getBookCSV(id: String): String {
        return ""
    }

    override fun updateBook(book: Book) {
    }
}