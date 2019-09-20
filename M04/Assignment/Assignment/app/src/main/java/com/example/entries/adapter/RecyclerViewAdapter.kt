package com.example.entries.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.entries.R
import com.example.entries.activity.EditBookActivity
import com.example.entries.model.Book
import com.example.entries.model.Constants
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter(private val books: MutableList<Book>): RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    lateinit var context: Context

    inner class Holder(view: View): RecyclerView.ViewHolder(view){
        var txtTitle = view.txt_title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(R.layout.recycler_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val book = books[position]
        val textView = holder.txtTitle
        textView.text = book.title

        textView.setOnClickListener {
            val intent = Intent(context, EditBookActivity::class.java)
            intent.putExtra(Constants.STRING_KEY, book.toCsvString())
            (context as? Activity)?.startActivityForResult(intent, Constants.EDIT_BOOK)

        }
    }
}