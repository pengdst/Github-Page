package io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper

import io.github.pengdst.githubpage.datas.domain.models.User
import io.github.pengdst.githubpage.datas.utils.mapper.ListMapper
import io.github.pengdst.githubpage.datas.utils.mapper.Mapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDto

object UserMapper : Mapper<UserDto, User> {

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
}