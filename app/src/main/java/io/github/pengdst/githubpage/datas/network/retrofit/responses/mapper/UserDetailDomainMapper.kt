package io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper

import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.domain.utils.DomainListMapper
import io.github.pengdst.githubpage.datas.domain.utils.DomainMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto

object UserDetailDomainMapper : DomainMapper<UserDetailDto, UserDetail>,
    DomainListMapper<UserDetailDto, UserDetail> {
    override fun toDomainModel(entity: UserDetailDto) = UserDetail(
        gistsUrl = entity.gistsUrl,
        reposUrl = entity.reposUrl,
        followingUrl = entity.followingUrl,
        twitterUsername = entity.twitterUsername,
        bio = entity.bio,
        createdAt = entity.createdAt,
        username = entity.username,
        type = entity.type,
        blog = entity.blog,
        subscriptionsUrl = entity.subscriptionsUrl,
        updatedAt = entity.updatedAt,
        siteAdmin = entity.siteAdmin,
        company = entity.company,
        id = entity.id,
        publicRepos = entity.publicRepos,
        gravatarId = entity.gravatarId,
        email = entity.email,
        organizationsUrl = entity.organizationsUrl,
        hireable = entity.hireable,
        starredUrl = entity.starredUrl,
        followersUrl = entity.followersUrl,
        publicGists = entity.publicGists,
        url = entity.url,
        receivedEventsUrl = entity.receivedEventsUrl,
        followers = entity.followers,
        avatarUrl = entity.avatarUrl,
        eventsUrl = entity.eventsUrl,
        htmlUrl = entity.htmlUrl,
        following = entity.following,
        name = entity.name,
        location = entity.location,
        nodeId = entity.nodeId
    )

    override fun fromDomainModel(domainModel: UserDetail) = UserDetailDto(
        gistsUrl = domainModel.gistsUrl,
        reposUrl = domainModel.reposUrl,
        followingUrl = domainModel.followingUrl,
        twitterUsername = domainModel.twitterUsername,
        bio = domainModel.bio,
        createdAt = domainModel.createdAt,
        username = domainModel.username,
        type = domainModel.type,
        blog = domainModel.blog,
        subscriptionsUrl = domainModel.subscriptionsUrl,
        updatedAt = domainModel.updatedAt,
        siteAdmin = domainModel.siteAdmin,
        company = domainModel.company,
        id = domainModel.id,
        publicRepos = domainModel.publicRepos,
        gravatarId = domainModel.gravatarId,
        email = domainModel.email,
        organizationsUrl = domainModel.organizationsUrl,
        hireable = domainModel.hireable,
        starredUrl = domainModel.starredUrl,
        followersUrl = domainModel.followersUrl,
        publicGists = domainModel.publicGists,
        url = domainModel.url,
        receivedEventsUrl = domainModel.receivedEventsUrl,
        followers = domainModel.followers,
        avatarUrl = domainModel.avatarUrl,
        eventsUrl = domainModel.eventsUrl,
        htmlUrl = domainModel.htmlUrl,
        following = domainModel.following,
        name = domainModel.name,
        location = domainModel.location,
        nodeId = domainModel.nodeId
    )

    override fun toDomainList(entity: List<UserDetailDto>) = entity.map {
        toDomainModel(it)
    }

    override fun fromDomainList(domainList: List<UserDetail>) = domainList.map {
        fromDomainModel(it)
    }
}