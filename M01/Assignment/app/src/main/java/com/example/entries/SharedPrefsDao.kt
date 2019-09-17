package com.example.entries

import android.util.Log

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
            Log.i("pleasework", "$i")
            MainActivity.preferences.edit()
                .putString(MainActivity.bookList[i].id, MainActivity.bookList[i].toCsvString())
                .apply()
        }
    }

//    val preferences: SharedPreferences = context.getSharedPreferences(MainActivity.USER_PREFERENCES, Context.MODE_PRIVATE)

//    fun getAllBookIds(): String{
//        var ids = ""
//        for (i in MainActivity.bookList.indices){
//            if (MainActivity.bookList.size -1 != i){
//                ids += "$i,"
//            }else{
//                ids += "$i"
//            }
//        }
//        return ids
//    }


    fun getAllBookIds(): String {
        return MainActivity.preferences.getString(ID_LIST, "") ?: ""
//        var idList = MainActivity.preferences.getString(ID_LIST, "")
//        val oldList = idList!!.split(",")
//        val ids = ArrayList<String>(oldList.size)
//        if (idList.isNotBlank()){
//            ids.addAll(oldList)
//        }
//        return ids
    }

//    fun getNextId(): String{
//        return MainActivity.bookList.size.toString()
//    }

//    fun getBookCSV(id: String): String{
//        return MainActivity.bookList[id.toInt()].toCsvString()
//    }

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