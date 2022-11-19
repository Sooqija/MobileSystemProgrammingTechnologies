package com.example.a05_frame_stack

import android.annotation.SuppressLint
import android.content.AttributionSource
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet

class MyAdapter(private val context: Context,
                private val layout: Int,
                private val taskSource: Array<String>,
                private val dateSource: Array<String>): BaseAdapter() {
                    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return taskSource.size
    }

    override fun getItem(position: Int): Any {
        return taskSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item, parent, false)
        var task = rowView.findViewById<TextView>(R.id.task)
        var date = rowView.findViewById<TextView>(R.id.date)

        task.text = taskSource[position]
        date.text = dateSource[position]


        return rowView
    }

}