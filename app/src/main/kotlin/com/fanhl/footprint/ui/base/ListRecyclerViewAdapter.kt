package com.fanhl.footprint.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fanhl.footprint.model.Record
import java.util.*

/**
 * Created by fanhl on 16/5/17.
 */
abstract class ListRecyclerViewAdapter<CVH : ClickableRecyclerViewAdapter.ClickableViewHolder, ITEM>(context: Context, recyclerView: RecyclerView) : ClickableRecyclerViewAdapter<CVH>(context, recyclerView) {
    val list = ArrayList<ITEM>()

    override fun getItemCount() = list.size

    fun addItem(item: ITEM) {
        val position = list.size
        list += item
        notifyItemInserted(position)
    }

    fun addItems(items: List<ITEM>) {
        val positionStart = list.size
        list += items
        notifyItemRangeInserted(positionStart, items.size)
    }

    fun clear() {
        val itemCount = list.size
        list.clear()
        notifyItemRangeRemoved(0, itemCount)
    }


    open class ListViewHolder(itemView: View) : ClickableViewHolder(itemView) {
        lateinit var data: Record

        init {
        }

        open fun bind(data: Record) {


            this.data = data
        }
    }
}