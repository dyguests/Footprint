package com.fanhl.footprint.ui.base

/**
 * Created by fanhl on 16/5/12.
 */
interface Listable<T> {
    val list: List<T>

    fun addItem(item: T)
    fun addItems(items: List<T>)
    fun clear()
}