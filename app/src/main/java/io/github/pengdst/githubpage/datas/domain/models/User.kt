package io.github.pengdst.githubpage.datas.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
	val name: String,
	val username: String,
	val avatar: String,
	val location: String,
	val company: String,
	val follower: Int,
	val following: Int,
	val repository: Int
) : Parcelable