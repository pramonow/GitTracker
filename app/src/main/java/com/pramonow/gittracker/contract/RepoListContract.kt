package com.pramonow.gittracker.contract

import com.pramonow.gittracker.model.RepoModel

interface RepoListContract {

    interface Activity{
       fun showRepoList(repoList:List<RepoModel>)
    }

    interface Presenter{
        fun getRepoList()
    }
}