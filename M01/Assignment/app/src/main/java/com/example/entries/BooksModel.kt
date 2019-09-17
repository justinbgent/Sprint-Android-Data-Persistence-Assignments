package com.example.entries

import android.content.Intent
import android.util.Log

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

//        fun populateBookList(){
//            MainActivity.bookList = mutableListOf<Book>(
//                Book("Skyward", "It's so good", true, "0"),
//                Book("Harry Potter", "Liked the movies", false, "1"),
//                Book("Way of Kings", "It's so good", true, "2"),
//                Book("Skyward,It's so good,true,3")
//            )
//        }

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