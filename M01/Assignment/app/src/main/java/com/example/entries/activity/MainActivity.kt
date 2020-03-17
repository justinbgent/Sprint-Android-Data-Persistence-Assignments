package com.example.entries.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entries.*
import com.example.entries.adapter.RecyclerViewAdapter
import com.example.entries.model.Book
import com.example.entries.model.BooksModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val STRING_KEY = "STRING_KEY"
        const val ID_KEY = "ID_KEY"
        const val ADD_BOOK = 345
        const val EDIT_BOOK = 545
        const val USER_PREFERENCES = "USER_PREFERENCES"

        var bookList = mutableListOf<Book>()

        lateinit var preferences: SharedPreferences
    }



    lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        preferences = this.getSharedPreferences(
            USER_PREFERENCES, Context.MODE_PRIVATE)

        BooksModel.updateBookList()
        SharedPrefsDao.saveAllBookCvs()
        SharedPrefsDao.saveAllIds()


        recycler_view.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter =
            RecyclerViewAdapter(bookList)
        recycler_view.layoutManager = manager
        recycler_view.adapter = adapter

        btn_add_book.setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra(ID_KEY, bookList.size.toString())
            startActivityForResult(intent,
                ADD_BOOK
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_BOOK && resultCode == Activity.RESULT_OK) {
            val bookCSV = data?.getStringExtra(STRING_KEY)
            if (bookCSV != null) {
                val book = Book(bookCSV)
                bookList.add(book)
                adapter.notifyDataSetChanged()

                SharedPrefsDao.saveAllBookCvs()
                SharedPrefsDao.saveAllIds()
            }
        }
        if (requestCode == EDIT_BOOK && resultCode == Activity.RESULT_OK) {
            BooksModel.handleEditActivityResult(data!!)
            adapter.notifyDataSetChanged()

        }
    }
}
