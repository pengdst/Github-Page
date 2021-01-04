package io.github.pengdst.githubpage.datas.dummies

import io.github.pengdst.githubpage.datas.domain.models.User

interface DummyData {
    fun getUserList(): List<User>
}