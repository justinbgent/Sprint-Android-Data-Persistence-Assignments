package com.example.entries

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter(private val books: MutableList<Book>): RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    inner class Holder(view: View): RecyclerView.ViewHolder(view){
        var linear = view.linear_layout_recycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount() = books.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var textView = TextView(holder.linear.context)
        textView.text = books[position].title

        textView.setOnClickListener {  }
        holder.linear.addView(textView)
    }
}