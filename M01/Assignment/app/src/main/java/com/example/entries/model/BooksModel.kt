package com.example.entries.model

import android.content.Intent
import android.util.Log
import com.example.entries.SharedPrefsDao
import com.example.entries.activity.MainActivity

class BooksModel {
    companion object{

        fun updateBookList()/*: MutableList<Book>*/ {
            val idList = SharedPrefsDao.getAllBookIds()
            val oldList = idList.split(",")
            val ids = ArrayList<String>(oldList.size)
            if (idList.isNotBlank()) {
                ids.addAll(oldList)
            }
            ids.forEach {
                Log.i("pleasework", it)
                val csvString = SharedPrefsDao.getBookCSV(it)
                SharedPrefsDao.updateBook(Book(csvString))
            }
        }

        fun handleEditActivityResult(intent: Intent){
            val bookCSV = intent.getStringExtra(MainActivity.STRING_KEY)
            if(bookCSV != null) {
                val book = Book(bookCSV)
                val index = book.id
                MainActivity.bookList[index!!.toInt()] = book
                SharedPrefsDao.saveAllBookCvs()
                SharedPrefsDao.saveAllIds()
            }
        }
    }
}