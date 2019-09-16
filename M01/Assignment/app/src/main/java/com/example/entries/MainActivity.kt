package com.example.entries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        const val STRING_KEY = "STRING_KEY"
        const val ID_KEY = "ID_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bookList = mutableListOf<Book>(
            Book("Skyward", "It's so good", true, "0"),
            Book("Harry Potter", "Liked the movies", false, "1"),
            Book("Way of Kings", "It's so good", true, "2"),
            Book("Skyward,It's so good,true,0")
        )

        //        bookList.forEach {
//            var textView = TextView(this)
//            textView.text = it.title
//            linear_layout.addView(textView)
//        }
        linear_layout.childCount

        recycler_view.setHasFixedSize(true)
        val manager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = RecyclerViewAdapter(bookList)
        recycler_view.layoutManager = manager
        recycler_view.adapter = adapter

        btn_add_book.setOnClickListener {
            val intent = Intent(this, EditBookActivity::class.java)
            intent.putExtra(ID_KEY, bookList.size.toString())
            startActivity(intent)
        }
    }
}
