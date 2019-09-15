package com.test.spacex.ui.main.adapter

import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.ui.base.BaseDiffCallBack

class LaunchesDiffCallback(oldList: List<LaunchModel>, newList: List<LaunchModel>) :
    BaseDiffCallBack<LaunchModel>(oldList, newList) {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldLaunchModel = oldList[oldItemPosition]
        val newLaunchModel = newList[newItemPosition]
        return oldLaunchModel == newLaunchModel

    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldLaunchModel = oldList[oldItemPosition]
        val newLaunchModel = newList[newItemPosition]
        return oldLaunchModel.flightNumber == newLaunchModel.flightNumber
                && oldLaunchModel.missionId == newLaunchModel.missionId
    }
}