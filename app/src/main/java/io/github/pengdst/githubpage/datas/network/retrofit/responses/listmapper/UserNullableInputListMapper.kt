package io.github.pengdst.githubpage.datas.network.retrofit.responses.listmapper

import io.github.pengdst.githubpage.datas.domain.models.User
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserMapper
import io.github.pengdst.githubpage.datas.utils.mapper.ListMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDto
import io.github.pengdst.githubpage.datas.utils.mapper.NullableFromListMapper

object UserNullableInputListMapper : NullableFromListMapper<UserDto, User> {

    override fun toDomainModel(entity: List<UserDto>?) = entity?.map {
        UserMapper.toDomainModel(it)
    } ?: emptyList()

    override fun fromDomainModel(domainModel: List<User>) = if (domainModel.isEmpty()) null else domainModel.map {
        UserMapper.fromDomainModel(it)
    }
}