package com.test.spacex.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<VM : BaseViewModel<*>> : AppCompatActivity() {

    private var viewModel: VM? = null

    @get:LayoutRes
    abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)

        viewModel = if (viewModel == null) getViewModel() else viewModel
    }

    abstract fun getViewModel(): VM

}