package com.example.entries.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entries.*
import com.example.entries.Controller.BooksController
import com.example.entries.adapter.RecyclerViewAdapter
import com.example.entries.model.Constants
import com.example.entries.sharedPref.SharedPrefOperations
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val sharedPrefOperations = SharedPrefOperations(this)
    lateinit var adapter: RecyclerViewAdapter
    private lateinit var fileStorage: FileStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        sharedPrefOperations.initiatePreferences()
//        sharedPrefOperations.updateBookList()
        fileStorage = FileStorage(this) // this variable need to be declared here otherwise app crashed yet sharedPrefOperations
                                                // was somehow fine being declared above.
        fileStorage.updateBookList()




        btn_add_book.setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra(Constants.ID_KEY, Constants.bookList.size.toString())
            this.startActivityForResult(intent,
                Constants.ADD_BOOK
            )
        }


        recycler_view.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = RecyclerViewAdapter(Constants.bookList)
        recycler_view.layoutManager = manager
        recycler_view.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.ADD_BOOK && resultCode == Activity.RESULT_OK) {
            BooksController.handleAddActivityResult(data!!)
//            sharedPrefOperations.saveUserPreferences()
            fileStorage.saveAllBooks()
            adapter.notifyDataSetChanged()
        }
        if (requestCode == Constants.EDIT_BOOK && resultCode == Activity.RESULT_OK) {
            BooksController.handleEditActivityResult(data!!)
//            sharedPrefOperations.saveUserPreferences()
            fileStorage.saveAllBooks()
            adapter.notifyDataSetChanged()

        }
    }
}
