package com.test.spacex.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.CheckBox
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.spacex.R
import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.ui.base.BaseActivity
import com.test.spacex.ui.detail.DetailActivity
import com.test.spacex.ui.main.adapter.LaunchesAdapter
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var adapter: LaunchesAdapter

    override val layoutId: Int
        get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivity()
        getViewModel().getLaunches()
    }

    private fun initActivity() {
        rv_launches.adapter = adapter

        adapter.setOnItemClickListener(object : LaunchesAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, launchModel: LaunchModel?) {
                DetailActivity.openDetailActivity(view?.context, launchModel)
            }
        })

        addSwipeRefresh()
        setListeners()
        initObservers()
    }


    private fun initObservers() {
        observeLaunches()
        observeLoading()
        observeError()
    }

    private fun observeLaunches() {
        getViewModel().launchesList.observe(this, Observer {
            swipe_refresh.isRefreshing = false
            data_parent.visibility = VISIBLE
            tv_error.visibility = GONE
            adapter.updateLaunches(it)
        })
    }

    private fun observeLoading() {
        getViewModel().isLoading.observe(this, Observer {
            if (it) {
                showProgressBar()
            } else {
                hideProgressBar()
            }
        })
    }

    private fun observeError() {
        getViewModel().error.observe(this, Observer {
            onErrorReceived(it)
        })
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    }


    private fun onErrorReceived(error: String?) {
        swipe_refresh.isRefreshing = false
        data_parent.visibility = GONE
        tv_error.text = error
        tv_error.visibility = VISIBLE
        Log.e("onErrorReceived", error)
    }

    private fun showProgressBar() {
        progress_circular.visibility = VISIBLE
    }


    private fun hideProgressBar() {
        progress_circular?.visibility = GONE
    }

    private fun setListeners() {
        checkbox_tbd.setOnClickListener {
            if ((it as CheckBox).isChecked) {
                getViewModel().filterLaunchesByTBD()
            } else {
                getViewModel().fetchOriginalLaunches()
            }
        }
    }


    private fun addSwipeRefresh() {
        swipe_refresh.setOnRefreshListener {
            checkbox_tbd.isChecked = false
            data_parent.visibility = GONE
            swipe_refresh.isRefreshing = true
            adapter.updateLaunches(null)
            getViewModel().getLaunches()
        }
    }


    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
