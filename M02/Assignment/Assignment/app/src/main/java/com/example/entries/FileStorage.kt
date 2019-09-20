package com.example.entries

import android.content.Context
import android.util.Log
import com.example.entries.model.Book
import com.example.entries.model.Constants
import org.json.JSONException
import org.json.JSONObject
import java.io.*

class FileStorage(private var context: Context): BookRepoInterface {
    override fun updateBookList() {
        val fileList = getJsonFileList() //function declared further down in class
        for (file in fileList){
            val json = getJsonFileContents(file) //function declared further down in class
            try {
                Constants.bookList.add(Book(JSONObject(json)))
            }catch (e: JSONException){
                Log.i("NotMyFile", "Yep")
            }
        }
    }

    private val storageDirectory = context.filesDir

    fun saveAllBooks(){
        Constants.bookList.forEach {
            createBookFile(it) //function shown below
        }
    }

    private fun createBookFile(book: Book){
        val bookString = book.toJsonObject().toString() //toJsonObject() is part of the book class and formats the book into .json
        val filename = "bookId${book.id}.json"
        saveToFile(filename, bookString) //function shown below
    }

    private fun saveToFile(fileName: String, bookString: String){ //this method doesn't use any functions made by me
        val directory = storageDirectory // this value is declared towards the top of the class context.filesdir
        val outputFile = File(directory, fileName)

        var writer: FileWriter? = null
        try {
            writer = FileWriter(outputFile)
            writer.write(bookString)
        }catch (e: IOException){ }
        finally {
            if (writer != null){
                try {
                    writer.close()
                }catch (e: IOException){}
            }
        }
    }

    private fun getJsonFileList(): ArrayList<String>{
        val fileNames = arrayListOf<String>()
        val fileList = storageDirectory.list()
        if (fileList != null){
            for (fileName in fileList){
                if(fileName.contains(".json")){
                    fileNames.add(fileName)
                }
            }
        }
        return fileNames
    }

    private fun getJsonFileContents(fileName: String): String{
        val inputFile = File(storageDirectory, fileName)
        var readString: String? = null
        var reader: FileReader? = null
        try {
            reader = FileReader(inputFile)
            readString = reader.readText()
        }catch (e: FileNotFoundException){ }
        finally {
            if (reader != null){
                try {
                    reader.close()
                }catch (e: IOException){}
            }
        }
        return readString ?: ""
    }


}