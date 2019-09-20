package com.example.entries.Controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entries.activity.MainActivity
import com.example.entries.adapter.RecyclerViewAdapter
import com.example.entries.model.Book
import com.example.entries.model.Constants
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

object BooksController: ViewModel() {
// unneeded with recycler view

    fun handleEditActivityResult(intent: Intent) {
        val bookCSV = intent.getStringExtra(Constants.STRING_KEY)
        if (bookCSV != null) {
            val book = Book(bookCSV)
            val index = book.id
            Constants.bookList[index!!.toInt()] = book
        }
    }

    fun handleAddActivityResult(intent: Intent) {
        val bookCSV = intent.getStringExtra(Constants.STRING_KEY)
        if (bookCSV != null) {
            val book = Book(bookCSV)
            Constants.bookList.add(book)
        }
    }
}

class UpdateListAsyncTask(context: Context, private var adapter: RecyclerViewAdapter): AsyncTask<Void, Void, MutableList<Book>>(){
    var listener: CommunicateThread? = null
    interface CommunicateThread{
        fun getBookList(bookList: MutableList<Book>)
    }

    init {
        if(context is CommunicateThread){
            listener = context
        }
    }

    override fun doInBackground(vararg p0: Void?): MutableList<Book> {
        return mutableListOf<Book>()
    }

    override fun onPostExecute(result: MutableList<Book>?) {
        if(result!=null){
            listener?.getBookList(result)
        }
    }
}