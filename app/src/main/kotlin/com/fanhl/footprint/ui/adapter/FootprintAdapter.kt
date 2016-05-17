package com.fanhl.footprint.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fanhl.footprint.R
import com.fanhl.footprint.model.Record
import com.fanhl.footprint.ui.base.ListRecyclerViewAdapter
import kotlinx.android.synthetic.main.view_footprint_item.view.*

class FootprintAdapter(context: Context, recyclerView: RecyclerView) : ListRecyclerViewAdapter<FootprintAdapter.ViewHolder, Record>(context, recyclerView) {

    var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_footprint_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(list[position])
    }

    inner class ViewHolder(itemView: View) : ListViewHolder(itemView) {
        override fun bind(data: Record) {
            super.bind(data)

            itemView.index_tv.text = data.index.toString()
            itemView.info_tv.text = data.name

            if (adapterPosition != selectedPosition) itemView.toolbar_ll.visibility = View.GONE
            else itemView.toolbar_ll.visibility = View.VISIBLE
            itemView.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

}