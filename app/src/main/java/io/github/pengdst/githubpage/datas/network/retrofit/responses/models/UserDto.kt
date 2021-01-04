package io.github.pengdst.githubpage.datas.network.retrofit.responses.models

import com.google.gson.annotations.SerializedName

data class UserDto(

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("login")
	val username: String,

	@field:SerializedName("avatar_url")
	val avatar: String,

	@field:SerializedName("location")
	val location: String,

	@field:SerializedName("company")
	val company: String,

	@field:SerializedName("follower")
	val follower: Int,

	@field:SerializedName("following")
	val following: Int,

	@field:SerializedName("repository")
	val repository: Int
)