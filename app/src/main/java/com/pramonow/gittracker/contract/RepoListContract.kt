package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.RepoModel

interface RepoListContract {

    interface Activity{
       fun showRepoList(repoList:List<RepoModel>)
       fun setLoading(boolean: Boolean)
    }

    interface Presenter{
        fun getRepoList(username:String, limit:Int, page:Int)
    }
}