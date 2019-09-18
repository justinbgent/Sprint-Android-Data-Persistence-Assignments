package com.example.entries.sharedPref

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.entries.BookRepoInterface
import com.example.entries.model.Book
import com.example.entries.model.Constants

class SharedPrefOperations(private val context: Context): BookRepoInterface {
    companion object {
        lateinit var preferences: SharedPreferences
    }

    private val sharedPrefsDao = SharedPrefsDao()

    override fun updateBookList()/*: MutableList<Book>*/ {
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
            Constants.USER_PREFERENCES, Context.MODE_PRIVATE
        )
    }

    fun saveUserPreferences(){
        sharedPrefsDao.saveAllBookCvs()
        sharedPrefsDao.saveAllIds()
    }
}