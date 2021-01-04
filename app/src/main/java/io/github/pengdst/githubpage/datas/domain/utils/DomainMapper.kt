package io.github.pengdst.githubpage.datas.domain.utils

interface DomainMapper<T, DomainModel> {

    fun toDomainModel(entity: T): DomainModel
    fun fromDomainModel(domainModel: DomainModel): T

}