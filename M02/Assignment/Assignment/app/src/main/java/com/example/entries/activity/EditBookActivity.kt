package com.example.entries.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.entries.model.Book
import com.example.entries.R
import com.example.entries.model.BooksModel
import kotlinx.android.synthetic.main.activity_edit_book.*

class EditBookActivity : AppCompatActivity() {

    private var hasBeenRead = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_book)

        var id = intent.getStringExtra(BooksModel.ID_KEY)

        val bookCSV = intent.getStringExtra(BooksModel.STRING_KEY)
        var book: Book? = if (bookCSV != null) Book(
            bookCSV
        ) else null

        if (book != null){
            checkbox.isChecked = book.hasBeenRead
            hasBeenRead = book.hasBeenRead
            id = book.id
            edt_txt_title.setText(book.title)
            edt_txt_reason.setText(book.reasonToRead)
        }else{
            checkbox.isChecked = false
        }

        checkbox.setOnClickListener {
            onCheckBoxClicked(checkbox)
        }

        fun returnData(): String{
            val title = edt_txt_title.text.toString()
            val reason = edt_txt_reason.text.toString()
            book = Book(
                title,
                reason,
                hasBeenRead,
                id ?: BooksModel.bookList.size.toString()
            )
            return book?.toCsvString() ?: "Empty"
        }

        btn_save.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(BooksModel.STRING_KEY, returnData())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        btn_cancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        returnData
//    }

    fun onCheckBoxClicked(view: View) {
        hasBeenRead = !hasBeenRead
    }
}
