package io.github.pengdst.githubpage.datas.network.retrofit.responses.listmapper

import io.github.pengdst.githubpage.datas.domain.models.User
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserMapper
import io.github.pengdst.githubpage.datas.utils.mapper.ListMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDto

object UserListMapper : ListMapper<UserDto, User> {

    override fun toDomainModel(entity: List<UserDto>) = entity.map {
        UserMapper.toDomainModel(it)
    }

    override fun fromDomainModel(domainModel: List<User>) = domainModel.map {
        UserMapper.fromDomainModel(it)
    }
}