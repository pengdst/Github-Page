package io.github.pengdst.githubpage.datas.domain.utils

import io.github.pengdst.githubpage.datas.domain.models.User
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserDetailDomainMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserDomainMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDto

fun User.toDto() = UserDomainMapper.fromDomainModel(this)
fun UserDto.toDomain() = UserDomainMapper.toDomainModel(this)

fun List<User>.toDtoList() = UserDomainMapper.fromDomainList(this)
fun List<UserDto>.toDomainList() = UserDomainMapper.toDomainList(this)

fun UserDetail.toDto() = UserDetailDomainMapper.fromDomainModel(this)
fun UserDetailDto.toDomain() = UserDetailDomainMapper.toDomainModel(this)

@JvmName("toDtoListUserDetail")
fun List<UserDetail>.toDtoList() = UserDetailDomainMapper.fromDomainList(this)
@JvmName("toDomainListUserDetailDto")
fun List<UserDetailDto>.toDomainList() = UserDetailDomainMapper.toDomainList(this)