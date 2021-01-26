package io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper

import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.utils.mapper.Mapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto

object UserDetailMapper : Mapper<UserDetailDto, UserDetail> {

    override fun toDomainModel(entity: UserDetailDto) = UserDetail(
        id = entity.id ?: -1,
        nodeId = entity.nodeId ?: "",
        twitterUsername = entity.twitterUsername ?: "",
        bio = entity.bio ?: "",
        createdAt = entity.createdAt ?: "",
        username = entity.username ?: "",
        type = entity.type ?: "",
        blog = entity.blog ?: "",
        updatedAt = entity.updatedAt ?: "",
        siteAdmin = entity.siteAdmin ?: false,
        company = entity.company ?: "",
        gravatarId = entity.gravatarId ?: "",
        email = entity.email ?: "",
        hireable = entity.hireable ?: "",
        name = entity.name ?: "",
        location = entity.location ?: "",
        following = UserDetail.Following(
            followingUrl = entity.followingUrl ?: "",
            following = entity.following ?: 0
        ),
        followers = UserDetail.Followers(
            followersUrl = entity.followersUrl ?: "",
            followers = entity.followers ?: 0
        ),
        url = UserDetail.Url(
            url = entity.url ?: "",
            htmlUrl = entity.htmlUrl ?: "",
            avatarUrl = entity.avatarUrl,
            organizationsUrl = entity.organizationsUrl ?: "",
            subscriptionsUrl = entity.subscriptionsUrl ?: "",
            starredUrl = entity.starredUrl ?: ""
        ),
        events = UserDetail.Events(
            receivedEventsUrl = entity.receivedEventsUrl ?: "",
            eventsUrl = entity.eventsUrl ?: ""
        ),
        repos = UserDetail.Repos(
            publicRepos = entity.publicRepos ?: 0,
            reposUrl = entity.reposUrl ?: ""),
        gists = UserDetail.Gists(
            gistsUrl = entity.gistsUrl ?: "",
            publicGists = entity.publicGists ?: 0),
    )

    override fun fromDomainModel(domainModel: UserDetail) = UserDetailDto(
        id = domainModel.id,
        nodeId = domainModel.nodeId,
        twitterUsername = domainModel.twitterUsername,
        bio = domainModel.bio,
        createdAt = domainModel.createdAt,
        username = domainModel.username,
        type = domainModel.type,
        blog = domainModel.blog,
        updatedAt = domainModel.updatedAt,
        siteAdmin = domainModel.siteAdmin,
        company = domainModel.company,
        gravatarId = domainModel.gravatarId,
        email = domainModel.email,
        hireable = domainModel.hireable,
        gistsUrl = domainModel.gists.gistsUrl,
        reposUrl = domainModel.repos.reposUrl,
        followingUrl = domainModel.following.followingUrl,
        subscriptionsUrl = domainModel.url.subscriptionsUrl,
        starredUrl = domainModel.url.starredUrl,
        followersUrl = domainModel.followers.followersUrl,
        publicGists = domainModel.gists.publicGists,
        publicRepos = domainModel.repos.publicRepos,
        url = domainModel.url.url,
        avatarUrl = domainModel.url.avatarUrl,
        htmlUrl = domainModel.url.htmlUrl,
        organizationsUrl = domainModel.url.organizationsUrl,
        followers = domainModel.followers.followers,
        following = domainModel.following.following,
        name = domainModel.name,
        location = domainModel.location,
        receivedEventsUrl = domainModel.events.receivedEventsUrl,
        eventsUrl = domainModel.events.eventsUrl
    )

}