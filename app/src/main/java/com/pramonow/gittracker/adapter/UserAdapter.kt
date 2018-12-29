package com.pramonow.gittracker.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.pramonow.gittracker.R
import com.pramonow.gittracker.model.RepoModel
import com.pramonow.gittracker.model.User
import com.pramonow.gittracker.util.AdapterOnClick
import com.squareup.picasso.Picasso
import java.util.ArrayList

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserVH> {

    var userList:MutableList<User> = ArrayList()
    var adapterOnClick: AdapterOnClick

    constructor(adapterOnClick: AdapterOnClick)
    {
        this.adapterOnClick = adapterOnClick
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): UserVH {

        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_user, viewGroup, false)
        return UserVH(view)
    }

    override fun onBindViewHolder(repoVH: UserVH, i: Int) {
        repoVH.setData(userList.get(i))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun addUserList(userList:List<User>)
    {
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    inner class UserVH : RecyclerView.ViewHolder {

        var userImage: ImageView
        var userName: TextView

        constructor(itemView: View) : super(itemView){
            userImage = itemView.findViewById(R.id.user_image)
            userName = itemView.findViewById(R.id.user_name)
        }

        fun setData(user: User)
        {
            Picasso.get().load(user.avatarUrl).placeholder(R.color.loading_block_gray).into(userImage)
            userName.setText(user.userName)
            itemView.setOnClickListener { v -> adapterOnClick.onClick(user.userName, "") }
        }

    }
}