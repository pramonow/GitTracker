package com.pramonow.gittracker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.pramonow.gittracker.model.RepoModel
import java.util.ArrayList

class RepoAdapter : RecyclerView.Adapter<RepoAdapter.RepoVH>() {

    var repoList:MutableList<RepoModel> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): RepoVH {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_repo, viewGroup, false)

        return RepoVH(view)
    }

    override fun onBindViewHolder(repoVH: RepoVH, i: Int) {
        repoVH.setData(repoList.get(i))
    }

    override fun getItemCount(): Int {
        return repoList.size
    }


    inner class RepoVH : RecyclerView.ViewHolder {

        lateinit var repoName:TextView

        constructor(itemView: View) : super(itemView){
            repoName = itemView.findViewById(R.id.repo_name)
        }

        fun setData(repoModel: RepoModel)
        {
            repoName.setText(repoModel.name)
        }

    }
}