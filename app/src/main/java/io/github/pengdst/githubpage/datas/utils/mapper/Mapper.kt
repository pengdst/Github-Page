package io.github.pengdst.githubpage.datas.utils.mapper

interface Mapper<T, DomainModel> {

    fun toDomainModel(entity: T): DomainModel
    fun fromDomainModel(domainModel: DomainModel): T

}