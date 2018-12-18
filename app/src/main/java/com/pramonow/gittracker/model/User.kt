package com.pramonow.gittracker.model

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import java.sql.Time

data class User(
    @SerializedName("login")
    var userName:String = "",
    @SerializedName("name")
    var name:String = "",
    @SerializedName("id")
    var id:Long = 0,
    @SerializedName("avatar_url")
    var avatarUrl:String = "",
    @SerializedName("public_repos")
    var publicRepoCount:Int = 0,
    @SerializedName("created_at")
    var createdTime:String = "",
    @SerializedName("updated_at")
    var updatedTime:String = ""
    )
    :Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readLong(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(name)
        parcel.writeLong(id)
        parcel.writeString(avatarUrl)
        parcel.writeInt(publicRepoCount)
        parcel.writeString(createdTime)
        parcel.writeString(updatedTime)
    }

    override fun describeContents()= 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}