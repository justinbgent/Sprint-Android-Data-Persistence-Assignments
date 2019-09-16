package com.example.entries

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        val id = intent.getStringExtra(MainActivity.ID_KEY)
        var hasBeenRead = false

        var bookCSV = intent.getStringExtra(MainActivity.STRING_KEY)
        var book: Book? = if (bookCSV != null) Book(bookCSV) else null

        if (book != null){
            checkbox.isChecked = book.hasBeenRead
        }else{
            checkbox.isChecked = false
        }

    }

    fun onCheckBoxClicked(view: View) {}
}
