package com.test.spacex.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.spacex.R
import com.test.spacex.data.server.models.LaunchModel

class LaunchesAdapter : RecyclerView.Adapter<LaunchesViewHolder>() {

    private val list: MutableList<LaunchModel> = ArrayList()

    private var onItemClickListener: OnItemClickListener? = null

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
            val diffResult = DiffUtil.calculateDiff(
                LaunchesDiffCallback(
                    list,
                    updatedList
                )
            )
            list.clear()
            list.addAll(updatedList)
            diffResult.dispatchUpdatesTo(this)
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_launch, parent, false)
        val holder = LaunchesViewHolder(view)
        holder.setOnItemClickListener(onItemClickListener)
        return holder
    }


    override fun onBindViewHolder(holder: LaunchesViewHolder, position: Int) {
        holder.updateView(list[position])
    }


    override fun getItemCount(): Int {
        return list.size
    }


    interface OnItemClickListener {
        fun onItemClick(view: View?, launchModel: LaunchModel?)
    }


}