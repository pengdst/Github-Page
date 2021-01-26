package io.github.pengdst.githubpage.datas.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class UserDetail(
	val id: Int,
	val gravatarId: String,
	val nodeId: String,
	val name: String,
	val bio: String,
	val username: String,
	val twitterUsername: String,
	val type: String,
	val blog: String,
	val siteAdmin: Boolean,
	val company: String,
	val email: String,
	val location: String,
	val createdAt: String,
	val updatedAt: String,
	val hireable: String,
	val repos: @RawValue Repos,
	val following: @RawValue Following,
	val followers: @RawValue Followers,
	val gists: @RawValue Gists,
	val events: @RawValue Events,
	val url: @RawValue Url
) : Parcelable{

	data class Repos(
		val publicRepos: Int,
		val reposUrl: String,
	)

	data class Following(
		val followingUrl: String,
		val following: Int,
	)

	data class Followers(
		val followersUrl: String,
		val followers: Int,
	)

	data class Gists(
		val gistsUrl: String,
		val publicGists: Int,
	)

	data class Events(
		val receivedEventsUrl: String,
		val eventsUrl: String,
	)

	data class Url(
		val url: String,
		val htmlUrl: String,
		val avatarUrl: String,
		val organizationsUrl: String,
		val subscriptionsUrl: String,
		val starredUrl: String,
	)
}