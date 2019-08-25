package com.test.spacex.ui.main

interface MainContract {

    fun onErrorReceived(error: String)

    fun showProgressBar()

    fun hideProgressBar()

}