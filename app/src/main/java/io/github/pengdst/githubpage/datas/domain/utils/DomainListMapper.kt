package io.github.pengdst.githubpage.datas.domain.utils

interface DomainListMapper<T, DomainModel> {

    fun toDomainList(entity: List<T>): List<DomainModel>
    fun fromDomainList(domainList: List<DomainModel>): List<T>

}