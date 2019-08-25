package com.test.spacex.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.spacex.utils.Consts
import com.test.spacex.R
import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.ui.base.BaseActivity
import com.test.spacex.ui.detail.DetailActivity.Companion.EXTRA_LAUNCH
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.include_common_detail.*
import javax.inject.Inject

fun Context.openDetailActivity(launch: LaunchModel) {
    val intent = Intent(this, DetailActivity::class.java)
    intent.putExtra(EXTRA_LAUNCH, launch)
    startActivity(intent)
}

class DetailActivity : BaseActivity<DetailViewModel>(), HasAndroidInjector {

    companion object {
        const val EXTRA_LAUNCH = "extra_launch"
    }


    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setData()
    }

    private fun setData() {
        val launch = intent.getSerializableExtra(EXTRA_LAUNCH) as LaunchModel

        tv_mission_name?.text = launch.mission_name
        tv_launch_date?.text = Consts.getFormattedDate(this, launch.launch_date_local)

        setMissionId(launch.mission_id)

        tv_detail?.text = launch.details
    }

    private fun setMissionId(missionId: List<String>) {
        tv_mission_id?.text = if (missionId.isNullOrEmpty()) {
            getString(R.string.txt_none)
        } else {
            missionId.toString()
        }
    }

    override val layoutId: Int
        get() = R.layout.activity_detail

    override fun getViewModel(): DetailViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}