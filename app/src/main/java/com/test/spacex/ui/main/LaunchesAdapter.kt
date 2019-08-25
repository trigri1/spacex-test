package com.test.spacex.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.spacex.R
import com.test.spacex.data.server.models.LaunchModel

class LaunchesAdapter : RecyclerView.Adapter<LaunchesViewHolder>() {

    private val list: MutableList<LaunchModel> = ArrayList()

    fun updateLaunches(updatedList: List<LaunchModel>?) {
        if (updatedList.isNullOrEmpty()) {
            list.clear()
            notifyDataSetChanged()
            return
        }

        if (list.isEmpty()) {
            list.addAll(updatedList)
            notifyDataSetChanged()
        } else {
            val diffResult = DiffUtil.calculateDiff(LaunchesDiffCallback(list, updatedList))
            list.clear()
            list.addAll(updatedList)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_launch, parent, false)
        return LaunchesViewHolder(view)
    }


    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        holder.updateView(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }


}