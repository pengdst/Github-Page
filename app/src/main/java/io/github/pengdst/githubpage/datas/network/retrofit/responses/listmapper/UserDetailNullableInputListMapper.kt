package io.github.pengdst.githubpage.datas.network.retrofit.responses.listmapper

import io.github.pengdst.githubpage.datas.domain.models.UserDetail
import io.github.pengdst.githubpage.datas.network.retrofit.responses.mapper.UserDetailMapper
import io.github.pengdst.githubpage.datas.network.retrofit.responses.models.UserDetailDto
import io.github.pengdst.githubpage.datas.utils.mapper.ListMapper
import io.github.pengdst.githubpage.datas.utils.mapper.NullableFromListMapper

/**
 *
 * Created by Pengkuh Dwi Septiandi on 1/26/21.
 *
 * Github    : https://github.com/pengdst
 * Gitlab    : https://gitlab.com/pengdst
 * LinkedIn  : https://linkedin.com/in/pengkuh-dst/
 *
 */

object UserDetailNullableInputListMapper: NullableFromListMapper<UserDetailDto, UserDetail> {

    override fun toDomainModel(entity: List<UserDetailDto>?) = entity?.map {
        UserDetailMapper.toDomainModel(it)
    } ?: emptyList()

    override fun fromDomainModel(domainModel: List<UserDetail>) = if (domainModel.isEmpty()) null else domainModel.map {
        UserDetailMapper.fromDomainModel(it)
    }

}