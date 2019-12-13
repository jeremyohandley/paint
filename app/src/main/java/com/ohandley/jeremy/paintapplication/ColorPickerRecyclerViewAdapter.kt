package com.ohandley.jeremy.paintapplication

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ColorPickerRecyclerViewAdapter(private val context: Context,
                                     private val viewModel:MainViewModel,
                                     private val parent:BottomSheetFragment) :
    RecyclerView.Adapter<ColorPickerRecyclerViewAdapter.ViewHolder>() {
    private var colors = viewModel.colors

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.color = colors!![position]
        holder.bindItems()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.color_recycler_view_item, parent, false))
    }

    override fun getItemCount(): Int {
        return colors!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var color:Int? = null

        fun bindItems() {
            val colorButton = itemView.findViewById<FloatingActionButton>(R.id.color_button)
            colorButton.backgroundTintList = ColorStateList.valueOf(color!!)
            colorButton.setOnClickListener{
                this@ColorPickerRecyclerViewAdapter.viewModel.color.postValue(color)
                this@ColorPickerRecyclerViewAdapter.parent.dismiss()
            }

        }

    }


}