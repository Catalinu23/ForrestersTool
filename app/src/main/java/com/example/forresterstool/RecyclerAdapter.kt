package com.example.forresterstool

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val context: Context, private val branches: MutableList<Branch>): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val typeTextView = itemView.findViewById<TextView>(R.id.typeTextView)
        val volumeTextView = itemView.findViewById<TextView>(R.id.volumeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val branch = branches[position]

        holder.typeTextView.text = branch.getType()
        holder.volumeTextView.text = branch.getVolume().toString()
    }

    override fun getItemCount(): Int {
        return branches.size
    }
}
