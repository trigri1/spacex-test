package com.test.spacex.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.spacex.utils.Consts
import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.ui.detail.openDetailActivity
import kotlinx.android.synthetic.main.include_common_detail.view.*

class LaunchesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun updateView(launchModel: LaunchModel) {
        itemView.tv_mission_name?.text = launchModel.mission_name
        itemView.tv_launch_date?.text =
            Consts.getFormattedDate(itemView.context, launchModel.launch_date_local)
        setMissionId(launchModel.mission_id)
        setListener(launchModel)
    }

    private fun setMissionId(missionId: List<String>) {

        itemView.tv_mission_id?.text = if (missionId.isNullOrEmpty()) {
            itemView.context.getString(com.test.spacex.R.string.txt_none)
        } else {
            missionId.toString()
        }
    }
    
    private fun setListener(launch: LaunchModel) {
        itemView.setOnClickListener {
            itemView.context.openDetailActivity(launch)
        }
    }


}