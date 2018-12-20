package com.pramonow.gittracker.presenter

import com.pramonow.gittracker.contract.UserProfileContract

class UserProfilePresenter:UserProfileContract.Presenter {

    private var activity: UserProfileContract.Activity

    constructor(activity: UserProfileContract.Activity) {
        this.activity = activity
    }
}