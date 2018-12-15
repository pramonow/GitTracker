package com.pramonow.gittracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import java.util.ArrayList

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoVH>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RepoVH {

        //val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_person, viewGroup, false)

        val view = View(null)
        return RepoVH(view)
    }

    override fun onBindViewHolder(sampleVH: RepoVH, i: Int) {
    }

    override fun getItemCount(): Int {
        return 5
    }


    inner class RepoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}