package io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper

import io.github.pengdst.githubpage.datas.domain.models.User
import io.github.pengdst.githubpage.datas.domain.utils.DomainListMapper
import io.github.pengdst.githubpage.datas.domain.utils.DomainMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDto

object UserDomainMapper : DomainMapper<UserDto, User>,
    DomainListMapper<UserDto, User> {

    override fun toDomainModel(entity: UserDto) = User(
        name = entity.name,
        username = entity.username,
        avatar = entity.avatar,
        location = entity.location,
        company = entity.company,
        follower = entity.follower,
        following = entity.following,
        repository = entity.repository
    )

    override fun fromDomainModel(domainModel: User) = UserDto(
        name = domainModel.name,
        username = domainModel.username,
        avatar = domainModel.avatar,
        location = domainModel.location,
        company = domainModel.company,
        follower = domainModel.follower,
        following = domainModel.following,
        repository = domainModel.repository
    )

    override fun toDomainList(entity: List<UserDto>) = entity.map {
        toDomainModel(it)
    }

    override fun fromDomainList(domainList: List<User>) = domainList.map {
        fromDomainModel(it)
    }
}