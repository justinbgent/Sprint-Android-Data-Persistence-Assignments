package com.example.entries

class SharedPrefsDao {
    companion object{
        const val ID_LIST = "ID_LIST"
        const val NEXT_ID = "NEXT_ID"
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

    fun getAllBookIds(): String{
        return MainActivity.preferences.getString(ID_LIST, "")?: ""
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

    fun getNextId(): String{
        return MainActivity.preferences.getString(NEXT_ID, "")?: ""
    }

//    fun getBookCSV(id: String): String{
//        return MainActivity.bookList[id.toInt()].toCsvString()
//    }

    fun getBookCSV(id: String): String{
        return MainActivity.preferences.getString(id, "")?: ""
    }

    fun updateBook(book: Book){
        MainActivity.bookList.add(book)
    }
}