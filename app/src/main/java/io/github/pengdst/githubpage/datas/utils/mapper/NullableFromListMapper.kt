package io.github.pengdst.githubpage.datas.utils.mapper

interface NullableFromListMapper<T, DomainModel>: Mapper<List<T>?, List<DomainModel>>