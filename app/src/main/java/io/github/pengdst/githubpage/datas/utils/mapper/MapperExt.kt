package io.github.pengdst.githubpage.datas.utils.mapper

import io.github.pengdst.githubpage.datas.domain.models.User
import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.network.retrofit.responses.listmapper.UserDetailListMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.listmapper.UserListMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserDetailMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDto

fun User.toDto() = UserMapper.fromDomainModel(this)
fun UserDto.toDomain() = UserMapper.toDomainModel(this)

fun List<User>.toDtoList() = UserListMapper.fromDomainModel(this)
fun List<UserDto>.toDomainList() = UserListMapper.toDomainModel(this)

fun UserDetail.toDto() = UserDetailMapper.fromDomainModel(this)
fun UserDetailDto.toDomain() = UserDetailMapper.toDomainModel(this)

@JvmName("toDtoListUserDetail")
fun List<UserDetail>.toDtoList() = UserDetailListMapper.fromDomainModel(this)
@JvmName("toDomainListUserDetailDto")
fun List<UserDetailDto>.toDomainList() = UserDetailListMapper.toDomainModel(this)