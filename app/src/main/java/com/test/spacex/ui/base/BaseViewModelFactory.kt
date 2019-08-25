package com.test.spacex.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BaseViewModelFactory<V : Any>(private var viewModel: V) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(viewModel.javaClass)) {
            return viewModel as T
        }
        throw IllegalArgumentException("unknown class exception")
    }
}