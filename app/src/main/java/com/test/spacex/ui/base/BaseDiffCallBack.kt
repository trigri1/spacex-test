package com.test.spacex.ui.base

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffCallBack<T>(val oldList: List<T>, val newList: List<T>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }


}