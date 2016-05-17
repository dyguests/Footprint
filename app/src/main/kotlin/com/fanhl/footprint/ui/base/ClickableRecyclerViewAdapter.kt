package com.fanhl.footprint.ui.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.fanhl.footprint.model.Record
import java.util.*

/**
 * Created by fanhl on 2016/3/16.
 */
abstract class ClickableRecyclerViewAdapter<CVH : ClickableRecyclerViewAdapter.ClickableViewHolder>(protected var context: Context, protected var mRecyclerView: RecyclerView) : RecyclerView.Adapter<CVH>() {
    protected var mListeners: MutableList<RecyclerView.OnScrollListener> = ArrayList()

    init {
        this.mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(rv: RecyclerView?, newState: Int) {
                for (listener in mListeners) {
                    listener.onScrollStateChanged(rv, newState)
                }
            }

            override fun onScrolled(rv: RecyclerView?, dx: Int, dy: Int) {
                for (listener in mListeners) {
                    listener.onScrolled(rv, dx, dy)
                }
            }
        })
    }

    fun addOnScrollListener(listener: RecyclerView.OnScrollListener) {
        mListeners.add(listener)
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int, holder: ClickableViewHolder)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int, holder: ClickableViewHolder): Boolean
    }

    private var itemClickListener: OnItemClickListener? = null
    private var itemLongClickListener: OnItemLongClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.itemLongClickListener = listener
    }

    override fun onBindViewHolder(holder: CVH, position: Int) {
        holder.itemView.setOnClickListener { itemClickListener?.onItemClick(position, holder) }
        holder.itemView.setOnLongClickListener { itemLongClickListener != null && itemLongClickListener!!.onItemLongClick(position, holder) }
    }

    open class ClickableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}