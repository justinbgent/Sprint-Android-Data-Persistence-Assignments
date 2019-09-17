package com.example.entries.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entries.*
import com.example.entries.adapter.RecyclerViewAdapter
import com.example.entries.model.BooksModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val booksModel = BooksModel()
    private val sharedPrefOperations = SharedPrefOperations(this)
    lateinit var adapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefOperations.initiatePreferences()
        sharedPrefOperations.updateBookList()

        btn_add_book.setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra(BooksModel.ID_KEY, BooksModel.bookList.size.toString())
            this.startActivityForResult(intent,
                BooksModel.ADD_BOOK
            )
        }


        recycler_view.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = RecyclerViewAdapter(BooksModel.bookList)
        recycler_view.layoutManager = manager
        recycler_view.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == BooksModel.ADD_BOOK && resultCode == Activity.RESULT_OK) {
            booksModel.handleAddActivityResult(data!!)
            sharedPrefOperations.saveUserPreferences()
            adapter.notifyDataSetChanged()
        }
        if (requestCode == BooksModel.EDIT_BOOK && resultCode == Activity.RESULT_OK) {
            booksModel.handleEditActivityResult(data!!)
            sharedPrefOperations.saveUserPreferences()
            adapter.notifyDataSetChanged()

        }
    }
}
