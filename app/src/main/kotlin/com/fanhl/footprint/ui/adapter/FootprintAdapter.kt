package com.fanhl.footprint.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanhl.footprint.model.Foot
import com.fanhl.footprint.ui.base.ClickableRecyclerViewAdapter
import com.fanhl.footprint.ui.base.Listable
import java.util.*

class FootprintAdapter(context: Context, recyclerView: RecyclerView) : ClickableRecyclerViewAdapter<FootprintAdapter.ViewHolder>(context, recyclerView), Listable<Foot> {
    var list = ArrayList<Foot>()

    init {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        throw UnsupportedOperationException()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        throw UnsupportedOperationException()
    }

    class ViewHolder(itemView: View) : ClickableViewHolder(itemView)
}

