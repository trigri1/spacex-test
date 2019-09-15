package com.test.spacex.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.spacex.data.server.models.LaunchModel
import com.test.spacex.utils.Consts
import kotlinx.android.synthetic.main.include_common_detail.view.*

class LaunchesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private var onItemClickListener: LaunchesAdapter.OnItemClickListener? = null

    fun updateView(launchModel: LaunchModel) {
        itemView.tv_mission_name?.text = launchModel.missionName
        itemView.tv_launch_date?.text =
            Consts.getFormattedDate(itemView.context, launchModel.launchDateLocal)
        setMissionId(launchModel.missionId)
        setListener(launchModel)
    }

    fun setOnItemClickListener(listener: LaunchesAdapter.OnItemClickListener?) {
        this.onItemClickListener = listener
    }

    private fun setMissionId(missionId: List<String?>?) {

        itemView.tv_mission_id?.text = if (missionId.isNullOrEmpty()) {
            itemView.context.getString(com.test.spacex.R.string.txt_none)
        } else {
            missionId.toString()
        }
    }

    private fun setListener(launch: LaunchModel) {
        itemView.setOnClickListener {
            onItemClickListener?.onItemClick(it, launch)
        }
    }
}