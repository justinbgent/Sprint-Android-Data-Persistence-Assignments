package com.example.entries

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter(private val books: MutableList<Book>): RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    lateinit var context: Context

    inner class Holder(view: View): RecyclerView.ViewHolder(view){
        var linear = view.linear_layout_recycler
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
        var textView = TextView(holder.linear.context)
        textView.text = book.title

        textView.setOnClickListener {
            val intent = Intent(context, EditBookActivity::class.java)
            intent.putExtra(MainActivity.STRING_KEY, book.toCsvString())
            context.startActivity(intent)
        }
        holder.linear.addView(textView)
    }
}